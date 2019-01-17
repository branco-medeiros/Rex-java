package rex.matchers;

import rex.Matcher;
import rex.interfaces.Capture;
import rex.interfaces.Context;
import rex.types.Stash;

public class CapMatcher extends ValueMatcher{

	private String id;

	public CapMatcher(String id, Matcher value) {
		super(value);
		this.id = id;
	}
	
	@Override
	public boolean match(Context ctx) {
		Capture c = ctx.newCapture(id, ctx.position(), null);
		Stash<Capture> vars = ctx.result().vars();
		int count = vars.size();
		vars.push(c);
		boolean ret = value.match(ctx);
		if(ret) {
			c.end(ctx.position());
		} else {
			vars.pop(count);
		}
		return ret;
	}

}
