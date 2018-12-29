package rex.types;

import java.util.List;

import rex.matchers.Rule;

public abstract class ContextBase<T> extends SeqT<T> implements Context{
	
	private Stk<ParseResult> result = Create.stk();
	private ParseResult root = null;

	public ContextBase(Lst<T> src) {
		super(src);
		root = new ParseResult(null, this);
		result.push(root);
	}

	@Override
	public ParseResult result(){
		return this.result.peek();
	}
	
	@Override
	public ParseResult root(){
		return root;
	}
	
	@Override
	public ParseResult enter(Rule rule) {
		ParseResult r = new ParseResult(rule, this);
		result.push(r);
		return r;
	}
	
	@Override
	public ParseResult leave(boolean result) {
		ParseResult r = this.result.pop();
		ParseResult prev = this.result.peek();
		if(result) prev.children().push(r);
		return r;
	}
	
	@Override
	public ParseResult swap(ParseResult other){
		if(result.count() == 0) throw new RuntimeException("can't swap at root");
		if(other == null) throw new NullPointerException("other");
		return result.swap(other);
	}

	@Override
	public ContextBase<T> setPosition(int value) {
		super.setPosition(value);
		return this;
	}


	@Override
	public boolean matches(int index, Object value) {
		T cur = get(index);
		if(cur == null || value == null) return false;
		return value.equals(cur) || cur.equals(value);
	}

	@Override
	public boolean matches(Object value) {
		return matches(this.position(), value);
	}

	@Override
	public List<ParseResult> trace() {
		return result.toList();
	}
	
	@Override
	public ParseResult find(Predicate<ParseResult> fn) {
		return result.each(fn);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Iterable range(int start, Integer end) {
		return (Iterable) this.span(start, end);
	}
	
	@Override
	public Capture var(String id) {
		return this.result().vars().each(new Predicate<Capture>() {
			@Override
			public Boolean eval(Capture value, int index) {
				return value.matches(id);
			}
		});
	}
}
