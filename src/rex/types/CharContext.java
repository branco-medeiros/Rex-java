package rex.types;

import rex.interfaces.Lst;

public class CharContext extends TContext<Character>{

	public CharContext(Lst<Character> src) {
		super(src);
	}
	
	public CharContext(CharContext other) {
		super(other);
	}
	
	@Override
	public boolean matches(int index, Object other) {
		Character cur = this.get(index);
		if(cur == null || other == null) return false;
		if(other instanceof String) {
			String s = (String) other;
			return s.length() == 1 && cur.charValue() == s.charAt(0);
		}
		return other.equals(cur) || cur.equals(other);
	}

	@Override
	public CharContext clone() {
		return new CharContext(this);
	}
}
