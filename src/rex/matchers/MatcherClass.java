package rex.matchers;

import java.util.Arrays;

import rex.Matcher;
import rex.interfaces.Context;

public abstract class MatcherClass implements Matcher{
	
	@Override
	public abstract boolean match(Context ctx);
	
	public MatcherClass plus() {
		return new RepMatcher(1, null, this);
	}
	
	public MatcherClass star() {
		return new RepMatcher(null, null, this);
	}
	
	public MatcherClass opt() {
		return new RepMatcher(null, 1, this);
	}
	
	public MatcherClass or(Matcher other) {
		if(other == null) throw new NullPointerException("other");
		if(this instanceof OrMatcher) {
			OrMatcher ret = (OrMatcher) this;
			ret.list().add(other);
			return ret;
		}
		
		return new OrMatcher(Arrays.asList(this, other));
	}
	
	public MatcherClass and(Matcher other) {
		if(other == null) throw new NullPointerException("other");
		if(this instanceof AndMatcher) {
			AndMatcher ret = (AndMatcher) this;
			ret.list().add(other);
			return ret;
		}
		
		return new AndMatcher(Arrays.asList(this, other));
	}
	
	
	
}
