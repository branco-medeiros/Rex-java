package rex.types;

import java.util.List;

import rex.Matcher;
import rex.interfaces.Capture;
import rex.interfaces.Result;
import rex.matchers.Rule;
import rex.utils.Captures;
import rex.utils.Convert;

public class ParseResult<T> extends CaptureClass<T> implements Result {

	private Rule rule;
	private Result pending;
	private Matcher matcher;
	private Stash<Capture> vars;
	private Stash<Result> children;


	public ParseResult(ContextClass<T> ctx, Rule rule) {
		super(ctx, null);
		this.rule = rule;
		initVars();
	}

	public ParseResult(ContextClass<T> ctx, Rule rule, int start, Integer end) {
		super(ctx, null, start, end);
		this.rule = rule;
		initVars();
	}

	public ParseResult(ParseResult<T> other) {
		super(other.ctx(), null);
		this.rule = other.rule();
		this.vars = other.vars().getClone();
		this.children = other.children.getClone();
		this.matcher = other.matcher();
	}

	protected void initVars() {
		vars = new Stash<>();
		children = new Stash<>();
	}

	@Override
	public String id() {
		String n = rule == null? null: rule.name();
		return n == null? "": n;
	}

	public Rule rule() {
		return rule;
	}


	@Override
	public Stash<Capture> vars(){
		return vars;
	}
	
	@Override
	public List<Capture> varList(String id) {
		return Captures.getAll(this.vars, id);
	}
	
	@Override
	public Capture var(String id) {
		return Captures.get(this.vars, id);
	}
	

	protected ParseResult<T> vars(Stash<Capture> value) {
		vars = value;
		return this;
	}

	@Override
	public Stash<Result> children(){
		return children;
	}

	protected ParseResult<T> children(Stash<Result> value) {
		children = value;
		return this;
	}

	@Override
	public ParseResult<T> start(int value) {
		super.start(value);
		return this;
	}

	@Override
	public ParseResult<T> end(Integer value) {
		super.end(value);
		return this;
	}

	@Override
	public Result pending() {
		return this.pending;
	}

	@Override
	public ParseResult<T> pending(Result value) {
		this.pending = value;
		return this;
	}

	@Override
	public Matcher matcher() {
		return this.matcher;
	}

	@Override
	public ParseResult<T> matcher(Matcher value) {
		this.matcher = value;
		return this;
	}

	@Override
	public ParseResult<T> getClone(){
		return new ParseResult<>(this);
	}
	

	@Override
	public String toString() {
		return Convert.toString((Result) this);
	}

}
