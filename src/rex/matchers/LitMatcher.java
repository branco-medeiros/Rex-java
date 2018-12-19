package rex.matchers;

import rex.Context;

public class LitMatcher extends MatcherBase{

	private Object value;
	
	public LitMatcher(Object value) {
		if(value == null) throw new NullPointerException("value");
		this.value = value;
	}
	
	public Context match(Context ctx) {
		if(!ctx.match(this.value)) return ctx.fail();
		ctx.moveNext();
		return ctx;
	}

}
