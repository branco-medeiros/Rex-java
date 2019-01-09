package rex.types;

import rex.interfaces.Lst;
import rex.utils.Types;

public class CharContext extends TContext<Character>{

	public CharContext(Lst<Character> src) {
		super(src);
	}
	
	public CharContext(CharContext other) {
		super(other);
	}
	
	@Override
	public boolean matches(int index, Object other) {
		return super.matches(index, Types.getChar(other));
	}
	
	@Override
	public boolean inRange(int position, Object first, Object last) {
		return super.inRange(position, Types.getChar(first), Types.getChar(last));
	}

	@Override
	public CharContext dup() {
		return new CharContext(this);
	}
}
