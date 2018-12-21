package rex.matchers;

import rex.types.Context;
import rex.Matcher;

public class IsNotMatcher extends IsMatcher{

	public IsNotMatcher(Matcher value) {
		super(value);
	}
	
	@Override
	public boolean match(Context ctx) {
		return !super.match(ctx);
	}
	

}
