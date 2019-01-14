package rex.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayLst<T> extends BaseLst<T>  {

	private T[] list;
	
	public ArrayLst(T[] list) {
		if(list == null) throw new NullPointerException("list");
		this.list = list;
	}
	
	@Override
	protected T getAt(int index) {
		return list[index];
	}

	@Override
	public Integer count() {
		return list.length;
	}

	@Override
	public T[] toArray() {
		return list;
	}
	
	@Override
	public List<T> toList() {
		return Arrays.asList(this.toArray());
	}
	
	@Override
	public Iterable<T> range(int start, Integer end) {
		List<T> ret = new ArrayList<>();
		int max = end == null? count(): end.intValue();
		if(max > start) {
			for(int i = start; i < max; ++i) ret.add(list[i]);
		}
		return ret;
	}
	

}
