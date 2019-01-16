package rex.matchers;

import rex.Context;

public class EofMatcher extends MatcherClass{

	@Override
	public boolean match(Context ctx) {
		return ctx.finished();
	}
}
