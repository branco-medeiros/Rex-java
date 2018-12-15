package rex.matchers;

import rex.Context;
import rex.Matcher;

public class LitMatcher extends Matcher {

	private Object value;
	
	public LitMatcher(Object value) {
		if(value == null) throw new NullPointerException("value");
		this.value = value;
	}
	
	@Override
	public Context match(Context ctx) {
		if(!ctx.match(this.value)) return ctx.fail();
		ctx.moveNext();
		return ctx;
	}

}
