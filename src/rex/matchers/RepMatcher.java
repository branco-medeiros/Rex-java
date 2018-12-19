package rex.matchers;

import rex.Context;
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
	public Context match(Context ctx) {
		int p1 = ctx.getPosition();
		int count = 0;
		while(true) {
			int p2 = p1;
			Context ret = value.match(ctx);
			if(ret.getFailed()) break;
			ctx = ret;
			count += 1;
			if(max != null && count >= max) break;
			p1 = ctx.getPosition();
			if(p1 == p2) {
				if(min == null && max == null) break;
				if(max == null && count >= min) break;
			};
		}
		if(min != null && min < count) return ctx.fail();
		return ctx;
	}
	
}
