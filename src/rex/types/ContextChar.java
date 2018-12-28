package rex.types;

public class ContextChar extends ContextT<Character>{

	public ContextChar(Lst<Character> src) {
		super(src);
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

}
