package rex.matchers;

import rex.Context;

public class AnyMatcher extends MatcherClass{

	@Override
	public boolean match(Context ctx) {
		return ctx.moveNext();
	}

}
