package rex.matchers;

import rex.Context;

public class AnyMatcher extends MatcherBase{

	@Override
	public Context match(Context ctx) {
		Context ret = ctx.moveNext();
		return ret == null? ctx.fail(): ctx;
	}

}
