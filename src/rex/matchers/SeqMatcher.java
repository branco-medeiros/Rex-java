package rex.matchers;

import rex.interfaces.Context;

public class SeqMatcher extends IterableMatcher{

	@SuppressWarnings("rawtypes")
	public SeqMatcher(Iterable items) {
		super(items);
	}
	
	@Override
	public boolean match(Context ctx) {
		return matchIt(ctx, items);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean matchIt(Context ctx, Iterable items) {
		int count = 0;
		for(Object cur: items) {
			if(!ctx.matches(cur)) return false;
			count += 1;
		}
		return count > 0;
	}

}
