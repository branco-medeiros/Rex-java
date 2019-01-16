package rex.matchers;

import java.util.ArrayList;
import java.util.List;

import rex.Context;
import rex.Matcher;
import rex.interfaces.Predicate;
import rex.interfaces.Result;
import rex.types.ParseResult;

public class Rule extends ListMatcher implements Matcher{

	private static final List<Matcher> EMPTY_LIST = new ArrayList<Matcher>();
	
	private String name;
	
	public Rule(String name, List<Matcher> list) {
		super(EMPTY_LIST);
		this
			.setName(name)
			.setBody(list);
	}

	public Rule(String name) {
		super(EMPTY_LIST);
		this
			.setName(name);
	}
	
	public Rule() {
		super(EMPTY_LIST);
	}

	public String name() {
		return this.name;
	}

	public Rule setName(String value) {
		this.name = value;
		return this;
	}
	
	public List<Matcher> body(){
		return this.list;
	}
	
	public Rule setBody(List<Matcher> list) {
		if(list == null || list == EMPTY_LIST) list = new ArrayList<Matcher>();
		this.list = list;
		return this;
	}

	public Rule setBody(Matcher value) {
		if(value == null) return setBody(EMPTY_LIST);
		
		if(value instanceof OrMatcher) {
			List<Matcher> other = ((OrMatcher) value).list();
			other = new ArrayList<Matcher>(other);
			return this.setBody(other);
		};
		
		this.list = new ArrayList<Matcher>();
		this.list.add(value);
		return this;
	}

	public Rule assign(List<Matcher> list) {
		return setBody(list);
	}
	
	public Rule assign(Matcher value) {
		return setBody(value);
	}
	
	
	protected Result findLeftContext(Context ctx) {
		//finds a previous occurrence of this rule that is still
		//being processed
		int pos = ctx.position();
		final Rule rule = this;
		Result prev = ctx.trace().each(new Predicate<Result>() {
			@Override
			public Boolean eval(Result value, int index) {
				return value.rule() == rule && value.start() == pos;
			}
		});
		if(prev != null) {
			Result ret = prev.pending();
			if(ret == null) throw new RuntimeException("Infinite left recursion detected");
			return ret;
		}
		return null;
	}
	
	@Override
	public boolean match(Context ctx) {
		return match(ctx, 0);
	}
	
	public boolean match(Context ctx, int prec) {
		Result prev = findLeftContext(ctx);
		if(prev != null) {
			//early exit because of a previous left context recursion
			ctx.result().children().push(prev);
			ctx.position(prev.end());
			return true;
		}
		
		int start = ctx.position();
		Result result = ctx.enter(this);
		
		boolean matched = false;
		for(Matcher m: list) {
			if(m instanceof PrecMatcher) continue;
			ctx.position(start);
			if(m.match(ctx)) {
				result.matcher(m);
				matched = true;
				break;
			}
		}
		if(matched) {
			//processes left recursive rules
			result.end(ctx.position());
			while(matched) {
				Result cur = ctx.newResult(this).pending(result);
				matched = false;
				ctx.trace().swap(cur);
				for(Matcher m: list) {
					PrecMatcher pm = (PrecMatcher) m;
					if(pm == null || pm.precedence() <= prec) continue;
					ctx.position(start);
					matched = pm.match(ctx);
					if(matched) {
						cur.matcher(m);
						break;
					}
				}
				cur.pending(null);
				if(matched) {
					result = cur;
					result.end(ctx.position());
					
				} else {
					ctx.position(result.end());
					ctx.trace().swap(result);
					
				}
			} //while
			matched = true;
		} //if matched
		
		ctx.leave(matched);
		return matched;
	}

}
