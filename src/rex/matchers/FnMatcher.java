package rex.matchers;

import rex.types.Context;
import rex.types.ContextAction;

public class FnMatcher extends MatcherBase {

	private ContextAction fn;
	
	public FnMatcher(ContextAction fn) {
		if(fn == null) throw new NullPointerException("fn");
		this.fn = fn;
	}
	
	public ContextAction fn() {
		return fn;
	}
	
	@Override
	public boolean match(Context ctx) {
		return fn.run(ctx);
	}
	
}
