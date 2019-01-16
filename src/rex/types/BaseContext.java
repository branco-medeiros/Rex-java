package rex.types;

import rex.Context;
import rex.interfaces.Lst;
import rex.interfaces.Stk;
import rex.matchers.Rule;
import rex.utils.Create;

public abstract class BaseContext<T> extends TSeq<T> implements Context{

	private Stk<ParseResult> result = Create.stk();
	private ParseResult root = null;
	private Lst<T> source;

	public BaseContext(Lst<T> src) {
		super(src);
		source = src;
		root = new ParseResult(null, this);
		result.push(root);
	}

	public Lst<T> source(){
		return source;
	}

	@Override
	public ParseResult root() {
		return root;
	}
	
	@Override
	public ParseResult result(){
		return this.result.peek();
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
	public Stk<ParseResult> trace() {
		return result;
	}


	@Override
	public BaseContext<T> setPosition(int value) {
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
	public boolean inRange(Object first, Object last) {
		return inRange(position(), first, last);
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean inRange(int position, Object first, Object last) {
		T cur = get(position);
		Comparable<T> c1 = (Comparable<T>) first;
		Comparable<T> c2 = (Comparable<T>) last;

		if(cur == null || c1 == null || c2 == null) return false;
		return c1.compareTo(cur) >= 0 && c2.compareTo(cur) <= 0;
	}

	@Override
	public Iterable<T> range(int start, Integer end) {
		return this.span(start, end);
	}

	/*
	@Override
	public Capture var(String id) {
		return this.result().vars().each(new Predicate<Capture>() {
			@Override
			public Boolean eval(Capture value, int index) {
				return value.matches(id);
			}
		});
	}
	*/
	
	@Override
	public Context getClone() {
		throw new UnsupportedOperationException();
	}
	
}
