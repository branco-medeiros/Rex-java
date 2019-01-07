package rex.matchers;

import rex.types.Capture;
import rex.Matcher;
import rex.interfaces.Context;
import rex.interfaces.Stk;

public class CapMatcher extends ValueMatcher{

	private String id;

	public CapMatcher(String id, Matcher value) {
		super(value);
		this.id = id;
	}
	
	@Override
	public boolean match(Context ctx) {
		Capture c = new Capture(id, ctx);
		Stk<Capture> vars = ctx.result().vars();
		int count = vars.count();
		vars.push(c);
		boolean ret = value.match(ctx);
		c.setEnd(ctx.position());
		if(!ret) vars.pop(count);
		return ret;
	}

}
