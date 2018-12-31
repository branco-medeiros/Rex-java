package rex.matchers;

import java.util.Iterator;

import rex.interfaces.Context;

public class FirstOfMatcher extends IterableMatcher{

	@SuppressWarnings("rawtypes")
	public FirstOfMatcher(Iterable items) {
		super(items);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean match(Context ctx) {
		Iterator it = items.iterator();
		while(it.hasNext()) {
			Object cur = it.next();
			if(ctx.matches(cur)) { 
				ctx.moveNext();
				return true;
			}
		}
		return false;
	}

}
