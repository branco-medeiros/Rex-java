package rex.types;

import java.util.Arrays;
import java.util.List;

public class ArrayLst<T> extends BaseLst<T>  {

	private T[] list;
	
	public ArrayLst(T[] list) {
		if(list == null) throw new NullPointerException("list");
		this.list = list;
	}
	
	@Override
	public T get(int index) {
		index = getIndex(index);
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
	

}