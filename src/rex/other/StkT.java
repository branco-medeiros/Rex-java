package rex.other;

public class StkT<T> extends StkBase<T> {
	
	public StkT() {
		super();
	}
	
	public StkT(Stk<T> other) {
		super(other);
	}
	
	public StkT(T value) {
		super();
		this.push(value);
	}
}
