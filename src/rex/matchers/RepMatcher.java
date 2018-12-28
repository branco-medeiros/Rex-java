package rex.matchers;

import rex.types.Context;
import rex.types.ParseResult;
import rex.types.ParseResultState;
import rex.Matcher;

public class RepMatcher extends ValueMatcher{
	
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
		ParseResultState ps = pr.getState();
		
		boolean hasMin = min != null && min != 0;
		boolean hasMax = max != null && max != 0;
		
		int p1 = ctx.position();
		int count = 0;
		while(true) {
			int p2 = p1;
			if(!value.match(ctx)) break;
			count += 1;
			if(hasMax && count >= max) break;
			p1 = ctx.position();
			if(p1 == p2) {
				if(!hasMin && !hasMax) break;
				if(hasMin && count >= min) break;
			};
		}
		if(hasMin && count < min) {
			//discards any variables and children
			//built while repeating
			pr.setState(ps);
			return false;
		}
		return true;
	}
	
}
