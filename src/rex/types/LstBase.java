package rex.types;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class LstBase<T> implements Lst<T>{

	@SuppressWarnings("unchecked")
	protected Class<T> getType() {
		return (Class<T>) (
			(ParameterizedType)	
			getClass().getGenericSuperclass()
		).getActualTypeArguments()[0];
	}
	
	protected int getIndex(int index) {
		return index < 0? count() + index: index;
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
		if(range == null) throw new NullPointerException("range");
		return span(range.start(), range.end());
	}
	
	@Override
	public Spn<T> span(int start, Integer end) {
		int max = count();
		start = Math.min(Math.max(0, getIndex(start)),  max-1);
		end = end == null? max: Math.min(Math.max(0, getIndex(end)),  max);
		return new SpnT<T>(this, start, end);
	}

	@Override
	public Spn<T> slice(int start, int count) {
		start = getIndex(start);
		return span(start, start + count);
	}

	@Override
	public Iterator<T> iterator() {
		return new SeqT<T>(this);
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
	

}
