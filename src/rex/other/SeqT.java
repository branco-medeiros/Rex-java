package rex.other;

public class SeqT<T> extends SeqBase<T> {

	private Lst<T> lst;
	
	public SeqT(Lst<T> lst) {
		if(lst == null) throw new NullPointerException("lst");
		this.lst = lst;
	}
	
	@Override
	public T current() {
		return lst.get(pos);
	}

	@Override
	public T get(int index) {
		return lst.get(index);
	}

	@Override
	public Integer count() {
		return lst.count();
	}

	@Override
	public T[] toArray() {
		return lst.toArray();
	}

}
