package rex.matchers;

import rex.types.Context;

public class EofMatcher extends MatcherBase{

	@Override
	public boolean match(Context ctx) {
		return ctx.finished();
	}
}
