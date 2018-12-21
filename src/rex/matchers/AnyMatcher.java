package rex.matchers;

import rex.types.Context;

public class AnyMatcher extends MatcherBase{

	@Override
	public boolean match(Context ctx) {
		return ctx.moveNext();
	}

}
