package rex.matchers;

import rex.interfaces.Context;

public class AnyMatcher extends MatcherBase{

	@Override
	public boolean match(Context ctx) {
		return ctx.moveNext();
	}

}
