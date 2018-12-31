package rex.types;

import rex.interfaces.Lst;

public class TContext<T> extends BaseContext<T> {

	public TContext(Lst<T> src) {
		super(src);
	}

	public TContext<T> setPosition(int pos){
		super.setPosition(pos);
		return this;
	}
}
