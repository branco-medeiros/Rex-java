package rex.matchers;

import rex.interfaces.Context;

public class SeqMatcher extends IterableMatcher{

	public SeqMatcher(Iterable<?> items) {
		super(items);
	}
	
	@Override
	public boolean match(Context ctx) {
		return match(ctx, items);
	}
	
	public static boolean match(Context ctx, Iterable<?> items) {
		int count = 0;
		for(Object cur: items) {
			if(ctx.finished() || !ctx.matches(cur)) return false;
			ctx.moveNext();
			count += 1;
		}
		return count > 0;
	}

}
