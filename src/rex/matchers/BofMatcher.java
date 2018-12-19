package rex.matchers;

import rex.Context;

public class BofMatcher extends MatcherBase{

	@Override
	public Context match(Context ctx) {
		return ctx.getPosition() != 0? ctx.fail() : ctx;
	}
}
