package rex.contexts;

import rex.Context;

public class StringContext extends Context{

	private String value;
	
	public StringContext(String value) {
		if(value == null) throw new NullPointerException("value");
		this.value = value;
	}
	
	@Override
	public Object getAt(int position) {
		return this.getItem(position);
	}
	
	public Character getItem(int position) {
		if(position < 0 || position >= this.value.length()) return null;
		return value.charAt(position);
	}
	
	@Override
	public boolean getFinished() {
		int p = this.getPosition();
		return p < 0 || p >= this.value.length();
	}

	@Override
	public Context clone() {
		StringContext result = new StringContext(this.value);
		return result.assign(this);
	}

	
	
}
