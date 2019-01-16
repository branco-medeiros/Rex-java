package rex.matchers;

import rex.Context;
import rex.Matcher;

public class IsMatcher extends ValueMatcher{
	
	public IsMatcher(Matcher value) {
		super(value);
	}

	@Override
	public boolean match(Context ctx) {
		return value.match(ctx.getClone());
	}
}
