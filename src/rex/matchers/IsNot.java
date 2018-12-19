package rex.matchers;

import rex.Context;
import rex.Matcher;

public class IsNot extends ValueMatcher{

	public IsNot(Matcher value) {
		super(value);
	}
	
	@Override
	public Context match(Context ctx) {
		Context ret = value.match(ctx.clone());
		return ret.getFailed()? ctx: ctx.fail();
	}
	

}
