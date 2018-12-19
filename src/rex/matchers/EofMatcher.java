package rex.matchers;

import rex.Context;

public class EofMatcher extends MatcherBase{

	@Override
	public Context match(Context ctx) {
		return ctx.getFinished()? ctx: ctx.fail();
	}
}
