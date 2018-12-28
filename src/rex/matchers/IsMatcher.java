package rex.matchers;

import rex.types.Context;
import rex.types.ParseResultState;
import rex.Matcher;

public class IsMatcher extends ValueMatcher{
	
	public IsMatcher(Matcher value) {
		super(value);
	}

	@Override
	public boolean match(Context ctx) {
		int pos = ctx.position();
		ParseResultState ps = ctx.result().getState();
		
		boolean ret = value.match(ctx);
		ctx.setPosition(pos);
		ctx.result().setState(ps);
		return ret;
	}
}
