package rex.types;

import java.util.List;

import rex.Matcher;

public class MatchResult<T> {
	protected boolean matched;
	protected Lst<T> source;
	protected Context ctx;
	protected Matcher matcher;
	
	
	public MatchResult(Matcher matcher, Lst<T> source) {
		this.matched = false;
		this.source = source;
		this.ctx = Create.contextFrom(source);
		this.matcher = matcher;
	}

	public Lst<T> source(){
		return this.source;
	}
	
	
	public boolean matched() {
		return matched;
	}
	
	public ParseResult result() {
		return ctx.result();
	}

	public MatchResult<T> next() {
		return null;
	}
	
	
	public List<Capture> group(String id){
		Stk<Capture> stk = this.result().vars();
		
	}
	
}
