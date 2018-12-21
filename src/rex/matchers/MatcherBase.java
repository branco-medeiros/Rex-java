package rex.matchers;

import rex.types.Context;
import rex.Matcher;

public abstract class MatcherBase implements Matcher{
	
	@Override
	public abstract boolean match(Context ctx);
	
	public Matcher plus() {
		return new RepMatcher(1, null, this);
	}
	
	public Matcher star() {
		return new RepMatcher(null, null, this);
	}
	
	public Matcher opt() {
		return new RepMatcher(null, 1, this);
	}
	
	
	
	
}
