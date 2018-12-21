package rex.matchers;

import java.util.List;

import rex.Matcher;

public abstract class ListMatcher extends MatcherBase {
	
	protected List<Matcher> list;

	public ListMatcher(List<Matcher> list) {
		if(list == null) throw new NullPointerException("list");
		this.list = list;
	}
	
	public List<Matcher> list(){
		return this.list;
	}
	
	
}
