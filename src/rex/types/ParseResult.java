package rex.types;

import rex.Matcher;
import rex.matchers.Rule;

public class ParseResult extends Capture {

	private Rule rule;
	private ParseResult pending;
	private Matcher matcher;
	private Stk<Capture> vars = Create.stk();
	private Stk<ParseResult> children = Create.stk();
	
	
	public ParseResult(Rule rule, Context ctx) {
		super(rule == null? null: rule.name(), ctx);
	}

	public ParseResult(Rule rule, int start, Integer end) {
		super(rule == null? null: rule.name(), start, end);
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
	
	public Stk<Capture> vars(){
		return vars;
	}
	
	public Stk<ParseResult> children(){
		return children;
	}
	
	@Override
	public ParseResult setStart(int value) {
		super.setStart(value);
		return this;
	}
	
	@Override
	public ParseResult setEnd(Integer value) {
		super.setEnd(value);
		return this;
	}
	
	public ParseResult pending() {
		return this.pending;
	}
	
	public ParseResult setPending(ParseResult value) {
		this.pending = value;
		return this;
	}
	
	public Matcher matcher() {
		return this.matcher;
	}
	
	public ParseResult setMatcher(Matcher value) {
		this.matcher = value;
		return this;
	}
}
