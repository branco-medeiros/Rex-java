package rex.matchers;

import rex.types.Context;
import rex.types.ParseResult;
import rex.Matcher;

public class RepMatcher extends ValueMatcher{
	
	private Matcher value;
	private Integer min;
	private Integer max;

	public RepMatcher(Integer min, Integer max, Matcher value) {
		super(value);
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean match(Context ctx) {
		ParseResult pr = ctx.result();
		int vars = pr.vars().count();
		int children = pr.children().count();
		
		int p1 = ctx.position();
		int count = 0;
		while(true) {
			int p2 = p1;
			if(!value.match(ctx)) break;
			count += 1;
			if(max != null && count >= max) {
				ctx.setPosition(p1);
				break;
			}
			p1 = ctx.position();
			if(p1 == p2) {
				if(min == null && max == null) break;
				if(max == null && count >= min) break;
			};
		}
		if(min != null && min < count) {
			//discards any variables and children
			//built while repeating
			pr.vars().pop(vars);
			pr.children().pop(children);
			return false;
		}
		return true;
	}
	
}
