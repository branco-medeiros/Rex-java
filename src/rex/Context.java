package rex;

import java.util.List;

import rex.contexts.ListContext;
import rex.contexts.StringContext;
import rex.matchers.Rule;
import rex.types.Result;
import rex.types.Track;

public abstract class Context {

	public Context() {
		// TODO Auto-generated constructor stub
	}
	
	public Context(Context other) {
		this.assign(other);
	}
	
	
	private int position;
	private boolean failed;
	private Track<Result> result;
	
	public abstract Object getAt(int position);
	public abstract Context clone();
	
	public Object getCurrent() {
		return this.getAt(this.position);
	}
	
	public boolean getFinished() {
		return this.getCurrent() == null;
	}
	
	public boolean getFailed() {
		return this.failed;
	}
	
	public boolean getMatched() {
		return !this.failed;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int value) {
		this.position = value;
	}
	
	public boolean match(Object value) {
		return this.matchAt(this.position, value);
	}
	
	protected Track<Result> getResultTrack(){
		Result temp = this.getResult(); //forces inicialization
		return this.result;
	}
	
	public boolean isRootResult() {
		return this.result == null || this.result.count() == 1;
	}
	
	public Result getResult() {
		if(this.result == null) {
			this.result = new Track<Result>();
			this.result.setValue(new Result());
		}
		return this.result.getValue();
	}
	
	public Result addRuleResult(Rule rule) {
		Result r = new Result();
		r.setRule(rule);
		this.result.push(r);
		return r;
	}
	
	public Result popResult() {
		if(this.isRootResult()) throw new RuntimeException("root");
		return this.result.pop();
	}
	
	public Result swapResults(Result other) {
		if(this.isRootResult()) throw new Error("root");
		if(other == null) throw new NullPointerException("other");
		Result r = this.result.getValue();
		this.result.setValue(other);
		return r;
	}
	
	public Result popResultAsChild() {
		Result r = this.popResult();
		this.result.getValue().add(r);
		return r;
	}
	
	public boolean matchAt(int position, Object other) {
		Object cur = this.getAt(position);
		if(cur == null || other == null) return false;
		return other.equals(cur) || cur.equals(other);
	}
	
	public boolean inRange(Object start, Object end) {
		return inRangeAt(this.position, start, end);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean inRangeAt(int position, Object start, Object end) {
		Object cur = this.getAt(position);
		if(start == null || end == null || cur == null) return false;
		if(start instanceof Comparable && end instanceof Comparable) {
			return (((Comparable) start).compareTo(cur) <= 0) &&
					(((Comparable) end).compareTo(cur) >= 0);
		}
		if(cur instanceof Comparable) {
			Comparable c = (Comparable) cur;
			return c.compareTo(start) >= 0 && c.compareTo(end) <= 0; 
		}
		
		return false;
	}
	
	
	
	public Context fail() {
		this.failed = true;
		return this;
	}
	
	public Context match() {
		this.failed = false;
		return this;
	}
	
	public Context moveNext() {
		return this;
	}

	public Context assign(Context other) {
		if (other != null) {
			this.position = other.position;
			this.result = other.getResultTrack();
			this.failed = other.failed;
		}
		return this;
	}
	
	static <T> Context from(List<T> list) {
		return new ListContext<T>(list);
	}
	
	static Context from(String value) {
		return new StringContext(value);
	}
	
	
}
