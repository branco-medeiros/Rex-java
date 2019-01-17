package rex.matchers;

import rex.interfaces.Capture;
import rex.interfaces.Context;

public class ReMatcher extends MatcherClass{

	private String id;
	
	public ReMatcher(String id) {
		this.id = id;
	}
	
	public String id() {
		return id;
	}
	
	@Override
	public boolean match(Context ctx) {
		Capture cap = ctx.result().var(id);
		if(cap == null) return false;
		Iterable<?> iter = ctx.span(cap.start(), cap.end());
		return SeqMatcher.match(ctx, iter);
	}
	

}
