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
import rex.utils.Types;

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
		return index >= 0? index : Types.getIndex(index, count());
	}
	
	@Override
	public T get(int index) {
		index = getIndex(index);
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
		int max = count();
		int start = Types.inRange(0, max, range.start());
		int end = Types.inRange(0,  max, range.end());
		return new TSpn<T>(this, start, end);
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
		return range(limit.start(), limit.end());
	}
	
	@Override
	public Iterable<T> range(int start, Integer end) {
		int max = count();
		int first = Types.inMinRange(0, max, start);
		int last = Types.inRange(0, max, end);
		List<T> ret = new ArrayList<>();
		for(int i=first; i < last; ++i) ret.add(get(i));
		return ret;
	}
	
}
