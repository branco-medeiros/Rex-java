package rex.types;

import java.util.Iterator;
import java.util.List;

import rex.Context;
import rex.Match;
import rex.interfaces.MatchAction;
import rex.utils.Captures;

/***
 * returned from rex.find; can be used interactively to continue the search;
 * - source(): returns the source used in the parsing
 * - matched(): returns true or false according to the parsing result;
 * - result(): the actual parsing result(the rule hierachy and captures produced during parsing)
 * - next(): returns a new MatchResult of the appropriate type executing the last operation again
 * 			on the subject at the current position;
 * - start(): returns the starting position of the match
 * - end(): returns the ending position (if any) of the match
 * - group(id): returns a list with all captures matching id
 * - capture(id): returns the last occurrence of the the capture matching id
 * - span(id): returns a span over the capture matching id
 * - iterator(): returns an iterator for matchresults starting with the current one;
 *
 * Usage:
 * 	//as an enumerable
 *  for(MatchResult<Character> m: SomeGrammar.findIn(SomeText)){
 *  	System.out.println(m.span().toString())  	
 *	}
 */
public class MatchResult implements Match {
	protected boolean matched;
	protected MatchAction action;
	protected Context ctx;
	
	public MatchResult(MatchAction action, Context ctx, boolean matched, int pos) {
		this.matched = matched;
		this.ctx = ctx;
		this.action = action;
		ctx.setPosition(pos); 
	}

	public boolean matched() {
		return matched;
	}
	
	public ParseResult result() {
		return ctx.result();
	}

	public Match next() {
		if(!matched()) return this;
		Context ctx = this.ctx.getClone().setPosition(end());
		return action.eval(ctx);
	}
	
	public int start() {
		return ctx.result().start();
	}
	
	public Integer end() {
		return ctx.result().end();
	}
	
	/***
	 * returns all occurrences of a given id
	 */
	public List<Capture> group(String id){
		return Captures.getAll(this.result().vars(), id);
	}
	
	/***
	 * returns the first (more recent) occurrence of a given id
	 */
	public Capture capture(String id) {
		return Captures.get(this.result().vars(), id);
	}
	
	@Override
	public Iterator<Match> iterator() {
		return new MatchIterator(this);
	}
	
	public static MatchResult fail(Context ctx) {
		return new MatchResult(null, ctx, false, ctx.position());
	}
}
