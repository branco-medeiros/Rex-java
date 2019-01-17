package rex.matchers;

import java.util.List;

import rex.Matcher;
import rex.interfaces.Context;

public class OrMatcher extends ListMatcher{

	public OrMatcher(List<Matcher> list) {
		super(list);
	}

	@Override
	public boolean match(Context ctx) {
		int pos = ctx.position();
		for(Matcher m: list) {
			ctx.position(pos);
			if(m.match(ctx)) return true;
		}
		return false;
	}
	
	

}
