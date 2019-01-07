package rex.matchers;

import rex.interfaces.Context;
import rex.types.Capture;

public class ReMatcher extends MatcherBase{

	private String id;
	
	public ReMatcher(String id) {
		this.id = id;
	}
	
	public String id() {
		return id;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean match(Context ctx) {
		Capture cap = ctx.var(id);
		if(cap == null) return false;
		Iterable iter = ctx.range(cap.start(), cap.end());
		return SeqMatcher.matchIt(ctx, iter);
	}
	

}
