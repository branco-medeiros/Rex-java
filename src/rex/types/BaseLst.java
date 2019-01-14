package rex.types;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rex.interfaces.Lst;
import rex.interfaces.Predicate;
import rex.interfaces.Range;
import rex.interfaces.Spn;
import rex.utils.Create;

public abstract class BaseLst<T> implements Lst<T>{

	@SuppressWarnings("unchecked")
	protected Class<T> getType() {
		return (Class<T>) (
			(ParameterizedType)	
			getClass().getGenericSuperclass()
		).getActualTypeArguments()[0];
	}
	
	protected abstract T getAt(int i);
	
	protected int getIndex(int index) {
		return index < 0? count() + index: index;
	}
	
	@Override
	public T get(int index) {
		index = getIndex(index); if(index < 0) return null;
		return getAt(index);
	}
	
	
	@Override
	public T each(Predicate<T> fn) {
		T ret = null;
		for(int i=0, max=count(); i < max; ++i) {
			ret = get(i);
			if(fn.eval(ret, i)) break;
		};
		return ret;
	}

	@Override
	public Spn<T> span(Range range) {
		range = normalize(range);
		return new TSpn<T>(this, range.start(), range.end());
	}
	
	@Override
	public Spn<T> span(int start, Integer end) {
		return span(Create.range(start, end));
	}

	@Override
	public Spn<T> slice(int start, int count) {
		start = getIndex(start);
		return span(start, start + count);
	}

	@Override
	public Iterator<T> iterator() {
		return new TSeq<T>(this);
	}

	@Override
	public List<T> toList() {
		List<T> ret = new ArrayList<T>(count());
		for(int i=0, max=count(); i < max; ++i) ret.set(i,  get(i));
		return ret;
	}
	
	@Override
	public Object[] toArray() {
		return this.toList().toArray();
	}

	@Override
	public T[] toArray(T[] ref) {
		return this.toList().toArray(ref);
	}
	
	
	@Override
	public Iterable<T> range(Range limit) {
		return getRange(normalize(limit));
	}
	
	@Override
	public Iterable<T> range(int start, Integer end) {
		return range(Create.range(start, end));
	}
	
	protected Iterable<T> getRange(Range limit) {
		int start = limit.start();
		int end = limit.end();
		List<T> ret = new ArrayList<>();
		for(int i=start; i < end; ++i) ret.add(get(i));
		return ret;
	}
	
	protected Range normalize(int start, Integer end) {
		return normalize(Create.range(start, end));
	}
	
	protected Range normalize(Range value) {
		int start = value.start();
		int cnt = count();
		Integer end = value.end();
		int max = cnt;
		if(start >= 0 && end != null && end <= cnt) return value;
		start = Math.min(Math.max(0, getIndex(start)),  cnt-1);
		max = end == null? cnt: Math.min(Math.max(0, getIndex(end)),  cnt);
		return Create.range(start, max);
	}

}
