package rex.matchers;

import rex.Context;

public class AnyMatcher extends MatcherBase{

	@Override
	public boolean match(Context ctx) {
		return ctx.moveNext();
	}

}
