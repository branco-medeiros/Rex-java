package rex.matchers;

import rex.interfaces.Context;

public class EofMatcher extends MatcherClass{

	@Override
	public boolean match(Context ctx) {
		return ctx.finished();
	}
}
