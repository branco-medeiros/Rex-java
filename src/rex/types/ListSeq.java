package rex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListSeq<T> implements Seq<T>{
	private List<T> list;

	public ListSeq(List<T> list) {
		if(list == null) throw new NullPointerException("list");
		this.list = list;
	}
	
	
	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public int count() {
		return list.size();
	}

	@Override
	public List<T> toList() {
		return new ArrayList<T>(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		return (T[]) list.toArray();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
	
	
}
