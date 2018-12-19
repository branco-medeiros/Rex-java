package rex.matchers;

import rex.Matcher;

public abstract class ValueMatcher extends MatcherBase{
	
	protected Matcher value;
	
	public ValueMatcher(Matcher value) {
		if(value == null) throw new NullPointerException("value");
		this.value = value;
	}
		

}
