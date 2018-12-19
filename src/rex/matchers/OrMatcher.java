package rex.matchers;

import java.util.List;

import rex.Context;
import rex.Matcher;

public class OrMatcher extends ListMatcher{

	public OrMatcher(List<Matcher> list) {
		super(list);
	}

	@Override
	public Context match(Context ctx) {
		Context bad = null;
		for(Matcher m: list) {
			Context ret = m.match(ctx.clone());
			if(!ret.getFailed()) return ret;
			if(bad == null || bad.getPosition() < ret.getPosition()) bad = ret;
		}
		return bad == null? ctx.fail() : bad;
	}
	
	

}
