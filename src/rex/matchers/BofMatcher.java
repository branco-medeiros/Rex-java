package rex.matchers;

import rex.Context;

public class BofMatcher extends MatcherBase{

	@Override
	public boolean match(Context ctx) {
		return ctx.position() == 0;
	}
}
