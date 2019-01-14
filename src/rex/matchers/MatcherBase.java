package rex.matchers;

import java.util.Arrays;

import rex.Context;
import rex.Matcher;
import rex.interfaces.RexMatcher;

public abstract class MatcherBase implements RexMatcher{
	
	@Override
	public abstract boolean match(Context ctx);
	
	public RexMatcher plus() {
		return new RepMatcher(1, null, this);
	}
	
	public RexMatcher star() {
		return new RepMatcher(null, null, this);
	}
	
	public RexMatcher opt() {
		return new RepMatcher(null, 1, this);
	}
	
	public RexMatcher or(Matcher other) {
		if(other == null) throw new NullPointerException("other");
		if(this instanceof OrMatcher) {
			OrMatcher ret = (OrMatcher) this;
			ret.list().add(other);
			return ret;
		}
		
		return new OrMatcher(Arrays.asList(this, other));
	}
	
	public RexMatcher and(Matcher other) {
		if(other == null) throw new NullPointerException("other");
		if(this instanceof AndMatcher) {
			AndMatcher ret = (AndMatcher) this;
			ret.list().add(other);
			return ret;
		}
		
		return new AndMatcher(Arrays.asList(this, other));
	}
	
	
	
}
