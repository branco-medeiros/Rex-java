package rex.matchers;

import rex.interfaces.Context;

public class RuleMatcher extends MatcherClass {

	private int prec;
	private Rule rule;
	
	public RuleMatcher(Rule rule, int prec) {
		if(rule == null) throw new NullPointerException("rule");
		this.rule = rule;
		this.prec = prec;
	}
	@Override
	public boolean match(Context ctx) {
		return rule.match(ctx, prec);
	}

}
