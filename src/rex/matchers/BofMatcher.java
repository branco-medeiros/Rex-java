package rex.matchers;

import rex.interfaces.Context;

public class BofMatcher extends MatcherClass{

	@Override
	public boolean match(Context ctx) {
		return ctx.position() == 0;
	}
}
