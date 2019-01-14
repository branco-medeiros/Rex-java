package rex.matchers;

import rex.Context;

public class EofMatcher extends MatcherBase{

	@Override
	public boolean match(Context ctx) {
		return ctx.finished();
	}
}
