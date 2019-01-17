package rex.matchers;

import rex.Matcher;
import rex.interfaces.Context;

public class IsMatcher extends ValueMatcher{
	
	public IsMatcher(Matcher value) {
		super(value);
	}

	@Override
	public boolean match(Context ctx) {
		return value.match(ctx.getClone());
	}
}
