package rex.matchers;

import java.util.Iterator;

import rex.Context;

public class FirstOfMatcher extends IterableMatcher{

	@SuppressWarnings("rawtypes")
	public FirstOfMatcher(Iterable items) {
		super(items);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Context match(Context ctx) {
		Iterator it = items.iterator();
		while(it.hasNext()) {
			if(ctx.match(it.next())) {
				if(ctx.moveNext() == null) break;
				return ctx;
			}
		}
		return ctx.fail();
	}

}
