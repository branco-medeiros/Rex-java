package rex.matchers;

import java.util.List;

import rex.Matcher;
import rex.interfaces.Context;

public class AndMatcher extends ListMatcher implements Matcher {

	public AndMatcher(List<Matcher> list) {
		super(list);
	}
	
	@Override
	public boolean match(Context ctx) {
		for(Matcher m: list) {
			if(!m.match(ctx)) return false;
		}
		return true;
	}


}
