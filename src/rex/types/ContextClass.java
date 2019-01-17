package rex.types;

import java.util.List;

import rex.interfaces.Capture;
import rex.interfaces.Context;
import rex.interfaces.Result;
import rex.matchers.Rule;
import rex.utils.Convert;

public class ContextClass<T> extends Sequence<T> implements Context{

	protected Stash<Result> result = new Stash<>();
	protected Result root = null;

	public ContextClass(List<T> src) {
		super(src);
		root = newResult(null);
		result.push(root);
	}

	public ContextClass(ContextClass<T> other) {
		super(other);
		result = other.result.getClone();
		root = other.root();
	}
	
	@Override
	public Result root() {
		return root;
	}
	
	@Override
	public Result result(){
		return this.result.peek();
	}

	@Override
	public Result enter(Rule rule) {
		Result r = newResult(rule);
		result.push(r);
		return r;
	}

	@Override
	public Result leave(boolean result) {
		Result r = this.result.pop();
		Result prev = this.result.peek();
		if(result) prev.children().push(r);
		return r;
	}
	
	@Override
	public Stash<Result> trace() {
		return result;
	}

	@Override
	public ContextClass<T> position(int value) {
		super.position(value);
		return this;
	}
	
	@Override
	public boolean matches(int index, Object value) {
		T cur = get(index);
		if(cur == null || value == null) return false;
		if(cur instanceof Character) value = Convert.toChar(value);
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
	public List<?> span(int start, Integer end) {
		return theSpan(start,  end);
	}
	
	public List<T> theSpan(int start, Integer end){
		int count = size();
		start = Convert.inMinRange(0, count, start);
		end = Convert.inRange(0,  count,  end);
		return subList(start,  end);
	}
	
	
	@Override
	public ContextClass<T> getClone() {
		return new ContextClass<>(this);
	}
	
	@Override
	public ContextClass<T> assign(Context other) {
		if(other != null) {
			this.position(other.position());
			this.result.assign(other.trace());
			this.root = other.root();
		}
		
		return this;
	};
	
	@Override
	public Capture newCapture(String id, int start, Integer end) {
		return new CaptureClass<>(this, id, start, end);
	}
	
	@Override
	public Result newResult(Rule rule) {
		return new ResultClass<>(this, rule);
	}
	
}
