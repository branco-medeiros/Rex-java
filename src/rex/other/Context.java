package rex.other;

import rex.matchers.Rule;

public class Context<T> {
	private Seq<T> seq;
	private Stk<ParseResult<T>> result = Create.stk();
	private ParseResult<T> root = null;
	private Stk<ParseProgress> failure;
	private Stk<ParseProgress> progress = Create.stk();

	public Context(Lst<T> src) {
		seq = Create.seqFrom(src);
		root = new ParseResult<T>(null, seq);
		result.push(root);
	}
	
	public ParseResult<T> root(){
		return root;
	}
	
	public boolean leave(boolean result) {
		ParseProgress v = progress.pop();
		if(!result && (failure == null || failure.value().position() < v.position())) {
			failure = Create.stkFrom(progress); 
		}
		return result;
	}
	
	public Context<T> enter(Rule rule) {
		progress.push(new ParseProgress().setPosition(seq.position()).setRule(rule));
		return this;
	}
	
}
