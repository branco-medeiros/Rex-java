package rex.matchers;

import rex.interfaces.Context;

public class AnyMatcher extends MatcherClass{

	@Override
	public boolean match(Context ctx) {
		return ctx.moveNext();
	}

}
