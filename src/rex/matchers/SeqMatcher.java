package rex.matchers;

import java.util.Iterator;

import rex.Context;
import rex.Matcher;

public class SeqMatcher extends IterableMatcher{

	@SuppressWarnings("rawtypes")
	public SeqMatcher(Iterable items) {
		super(items);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Context match(Context ctx) {
		Iterator it = items.iterator();
		while(it.hasNext()) {
			if(!ctx.match(it.next()) || ctx.moveNext() == null) return ctx.fail();
		}
		return ctx;
	}
	

}
