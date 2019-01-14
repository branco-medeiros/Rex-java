package rex.matchers;

import rex.Context;
import rex.Matcher;

public class PrecMatcher extends ValueMatcher{

	private int precedence;
	
	public PrecMatcher(int precedence, Matcher value) {
		super(value);
		this.precedence = precedence;
	}
	
	public int precedence() {
		return this.precedence;
	}

	@Override
	public boolean match(Context ctx) {
		return value.match(ctx);
	}

}
