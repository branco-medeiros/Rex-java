package rex.interfaces;

import java.util.List;

import rex.Matcher;
import rex.matchers.Rule;
import rex.types.Stash;

public interface Result extends Capture{
	Rule rule();
	
	Result start(int start);
	Result end(Integer end);
	
	Matcher matcher();
	Result matcher(Matcher value);
	
	Stash<Capture> vars();
	Capture var(String id);
	List<Capture> varList(String id);
	
	Stash<Result> children();
	
	Result pending();
	Result pending(Result value);
}
