package rex.other;

public class LstArray<T> extends LstBase<T>  {

	private T[] list;
	
	public LstArray(T[] list) {
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
	

}
