package rex.matchers;

import rex.Context;

public class LitMatcher extends MatcherClass{

	private Object value;
	
	public LitMatcher(Object value) {
		if(value == null) throw new NullPointerException("value");
		this.value = value;
	}
	
	public boolean match(Context ctx) {
		if(!ctx.matches(this.value)) return false;
		ctx.moveNext();
		return true;
	}

}
