package rex.matchers;

import java.util.List;

import rex.Context;
import rex.Matcher;

public class AndMatcher extends ListMatcher  implements Matcher {

	public AndMatcher(List<Matcher> list) {
		super(list);
	}
	
	@Override
	public Context match(Context ctx) {
		for(Matcher m: list) {
			ctx = m.match(ctx);
			if(ctx.getFailed()) break;
		}
		return ctx;
	}

}
