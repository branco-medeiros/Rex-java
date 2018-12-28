package rex.types;

import java.util.Iterator;
import java.util.List;

public class LstT<T> extends LstBase<T>{

	private List<T> list;
	
	public LstT(List<T> list) {
		if(list == null) throw new NullPointerException("list");
		this.list = list;
	}
	
		@Override
	public T get(int index) {
		return list.get(getIndex(index));
	}

	@Override
	public Integer count() {
		return list.size();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public List<T> toList() {
		return list;
	}
	

}
