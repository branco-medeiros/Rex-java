package rex.matchers;

import rex.Matcher;
import rex.interfaces.Context;

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
		Context prev = ctx.getClone();
		boolean hasMin = min != null && min != 0;
		boolean hasMax = max != null && max != 0;
		
		int p1 = ctx.position();
		int count = 0;
		Context worker = ctx.getClone();
		while(true) {
			int p2 = p1;
			if(!value.match(worker)) break;
			ctx.assign(worker);
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
			ctx.assign(prev);
			return false;
		}
		return true;
	}
	
}
