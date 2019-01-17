package rex.matchers;

import rex.Matcher;
import rex.interfaces.Context;

public class IsNotMatcher extends IsMatcher{

	public IsNotMatcher(Matcher value) {
		super(value);
	}
	
	@Override
	public boolean match(Context ctx) {
		return !super.match(ctx);
	}
	

}
