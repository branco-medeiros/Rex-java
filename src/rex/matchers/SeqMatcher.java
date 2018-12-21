package rex.matchers;

import java.util.Iterator;

import rex.types.Context;

public class SeqMatcher extends IterableMatcher{

	@SuppressWarnings("rawtypes")
	public SeqMatcher(Iterable items) {
		super(items);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean match(Context ctx) {
		Iterator it = items.iterator();
		while(it.hasNext()) {
			Object cur = it.next();
			if(!ctx.matches(cur)) return false;
			ctx.moveNext();
		}
		return true;
	}
	

}
