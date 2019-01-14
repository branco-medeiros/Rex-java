package rex.types;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rex.Context;
import rex.Matcher;
import rex.interfaces.Stk;
import rex.matchers.Rule;
import rex.utils.Captures;
import rex.utils.Create;

public class ParseResult extends Capture {

	private Rule rule;
	private ParseResult pending;
	private Matcher matcher;
	private Stk<Capture> vars;
	private Stk<ParseResult> children;


	public ParseResult(Rule rule, Context ctx) {
		super(null, ctx);
		this.rule = rule;
		initVars();
	}

	public ParseResult(Rule rule, Context ctx, int start, Integer end) {
		super(null, ctx, start, end);
		this.rule = rule;
		initVars();
	}

	public ParseResult(ParseResult other) {
		super(other);
		this.rule = other.rule();
		this.vars = other.vars().getClone();
		this.children = other.children.getClone();
		this.matcher = other.matcher();
	}

	protected void initVars() {
		vars = Create.stk();
		children = Create.stk();
	}

	@Override
	public String id() {
		String n = rule == null? null: rule.name();
		return n == null? "": n;
	}

	public Rule rule() {
		return rule;
	}


	public Stk<Capture> vars(){
		return vars;
	}
	
	public Capture var(String id) {
		return Captures.get(this.vars, id);
	}
	
	public List<Capture> vars(String id) {
		return Captures.getAll(this.vars, id);
	}
	

	protected ParseResult setVars(Stk<Capture> value) {
		vars = value;
		return this;
	}

	public Stk<ParseResult> children(){
		return children;
	}

	protected ParseResult setChildren(Stk<ParseResult> value) {
		children = value;
		return this;
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

	public ParseResultState getState() {
		return new ParseResultState()
				.setVars(this.vars.count())
				.setChildren(this.children.count());
	}

	public ParseResult setState(ParseResultState value) {
		this.vars.pop(value.vars);
		this.children.pop(value.children);
		return this;
	}

	@Override
	public ParseResult getClone(){
		return new ParseResult(this);
	}

	@Override
	public String toString() {
		return ParseResult.toString(this);
	}

	public static String toString(ParseResult pr) {
		if(pr == null) return "[<NULL>]";
		StringBuilder s = new StringBuilder();
		s.append("[");
		s.append(pr.id());
		s.append(": ");
		s.append(pr.start());
		s.append(", ");
		Integer end = pr.end();
		s.append(end == null? "?": end.toString());
		s.append(" vars:{");
		Set<String> names = new HashSet<String>();
		for(Capture c: pr.vars().toList()) {
			names.add(c.id());
		}
		s.append(String.join(", ", names));

		s.append("} children:{");
		names = new HashSet<String>();
		for(ParseResult p: pr.children().toList()) {
			names.add(Capture.toString(p));
		}
		s.append(String.join(", ", names));
		s.append("}]");
		return s.toString();
	}
}
