package rex.types;

import java.util.Iterator;
import java.util.List;

import rex.Match;
import rex.interfaces.Capture;
import rex.interfaces.Context;
import rex.interfaces.MatchAction;
import rex.interfaces.Result;
import rex.utils.Captures;

public class MatchClass implements Match {
	protected boolean matched;
	protected MatchAction action;
	protected Context ctx;
	
	public MatchClass(MatchAction action, Context ctx, boolean matched, int pos) {
		this.matched = matched;
		this.ctx = ctx;
		this.action = action;
	}
	
	public MatchClass(Context ctx, boolean matched, int pos) {
		this(null, ctx, matched, pos);
	}
	
	
	public MatchAction action() {
		return action;
	}
	
	public MatchClass action(MatchAction value) {
		this.action = value;
		return this;
	}

	@Override
	public boolean matched() {
		return matched;
	}
	
	@Override
	public Result result() {
		return ctx.result();
	}

	@Override
	public Match next() {
		if(!matched()) return this;
		Context ctx = this.ctx.getClone().position(end());
		return action.eval(ctx);
	}
	
	@Override
	public int start() {
		return ctx.result().start();
	}
	
	@Override
	public Integer end() {
		return ctx.result().end();
	}
	
	/***
	 * returns all occurrences of a given id
	 */
	@Override
	public List<Capture> group(String id){
		return Captures.getAll(this.result().vars(), id);
	}
	
	@Override
	public List<?> value() {
		return ctx.span(start(), end());
	}
	
	/***
	 * returns the first (more recent) occurrence of a given id
	 */
	@Override
	public Capture capture(String id) {
		return Captures.get(this.result().vars(), id);
	}
	
	@Override
	public Iterator<Match> iterator() {
		return new MatchIterator(this);
	}
	
	public static MatchClass fail(Context ctx) {
		return new MatchClass(ctx, false, ctx.position());
	}

}
