package rex.interfaces;

import rex.Matcher;

public interface RexMatcher extends Matcher {
	RexMatcher opt();
	RexMatcher star();
	RexMatcher plus();
	RexMatcher and(Matcher other);
	RexMatcher or(Matcher other);
	
}
