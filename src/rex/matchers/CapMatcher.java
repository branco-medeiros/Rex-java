package rex.matchers;

import rex.Context;
import rex.Matcher;
import rex.types.Result;
import rex.types.Span;

public class CapMatcher extends ValueMatcher{

	private String id;

	public CapMatcher(String id, Matcher value) {
		super(value);
		this.id = id;
	}
	
	@Override
	public Context match(Context ctx) {
		Result r = ctx.getResult();
		int children = r.count();
		Span s = new Span();
		s.setStart(ctx.getPosition());
		int idx = r.count(id) - 1;
		r.push(id, s);
		ctx = value.match(ctx);
		if(ctx.getFailed()) {
			r.pop(idx);
		} else {
			if(r.count() - children == 1) {
				//uses the resulting rule as value for the span
				r.put(id, idx, r.peek());
			} else {
				s.setEnd(ctx.getPosition());
			}
		}
		return ctx;
	}

}
