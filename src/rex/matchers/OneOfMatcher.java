package rex.matchers;

import rex.interfaces.Context;

public class OneOfMatcher extends IterableMatcher{

	public OneOfMatcher(Iterable<?> items) {
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
