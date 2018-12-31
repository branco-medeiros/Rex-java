package rex.types;

import rex.interfaces.Lst;

public class TSeq<T> extends BaseSeq<T> {

	private Lst<T> lst;
	
	public TSeq(Lst<T> lst) {
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


}
