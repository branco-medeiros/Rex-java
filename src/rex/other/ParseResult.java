package rex.other;

import rex.matchers.Rule;

public class ParseResult<T> extends Capture<T> {

	private Rule rule;
	private Stk<Capture<T>> vars = Create.stk();
	private Stk<ParseResult<T>> children = Create.stk();
	
	public ParseResult(Rule rule, Seq<T> src) {
		super(rule == null? null: rule.name(), src);
	}

	@Override
	public String id() {
		Rule r = rule();
		String n = r == null? null: r.name();
		return n == null? "": n;
	}
	
	public Rule rule() {
		return rule;
	}
	
	public Stk<Capture<T>> vars(){
		return vars;
	}
	
	public Stk<ParseResult<T>> children(){
		return children;
	}
	
}
