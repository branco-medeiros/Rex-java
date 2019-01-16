package rex.matchers;

import rex.Matcher;

public abstract class ValueMatcher extends MatcherClass{
	
	protected Matcher value;
	
	public ValueMatcher(Matcher value) {
		if(value == null) throw new NullPointerException("value");
		this.value = value;
	}
		
	public Matcher value() {
		return this.value;
	}
}
