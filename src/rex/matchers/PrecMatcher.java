package rex.matchers;

import rex.Matcher;
import rex.interfaces.Context;

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
