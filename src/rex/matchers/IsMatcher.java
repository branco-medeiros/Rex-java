package rex.matchers;

import rex.types.Context;
import rex.Matcher;

public class IsMatcher extends ValueMatcher{
	
	public IsMatcher(Matcher value) {
		super(value);
	}

	@Override
	public boolean match(Context ctx) {
		int pos = ctx.position();
		boolean ret = value.match(ctx);
		ctx.setPosition(pos);
		return ret;
	}
}
