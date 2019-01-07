package rex.types;

import rex.interfaces.Stk;

public class TStk<T> extends BaseStk<T> {
	
	public TStk() {
		super();
	}
	
	public TStk(Stk<T> other) {
		super(other);
	}
	
	public TStk(T value) {
		super();
		this.push(value);
	}
	
	@Override
	public TStk<T> clone(){
		return new TStk<T>(this);
	}
	

}
