package rex.matchers;

import rex.interfaces.Context;

public class FirstOfMatcher extends IterableMatcher{

	public FirstOfMatcher(Iterable<?> items) {
		super(items);
	}
	
	@Override
	public boolean match(Context ctx) {
		for(Object cur: items) {
			if(ctx.matches(cur)) {
				ctx.moveNext();
				return true;
			}
		}
		return false;
	}

}
