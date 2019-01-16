package rex;

import java.util.List;

import rex.interfaces.Capture;
import rex.interfaces.Result;
import rex.matchers.Rule;
import rex.types.Stash;

public interface Context{
	boolean finished();
	boolean moveNext();
	int position();
	Context position(int value);

	boolean matches(int position, Object value);
	boolean matches(Object value);
	boolean inRange(int position, Object first, Object last);
	boolean inRange(Object first, Object last);

	Result root();
	Result result();
	Result enter(Rule rule);
	Result leave(boolean Result);
	Stash<Result> trace();
	
	List<?> span(int start, Integer end);
	
	Context assign(Context other);
	Context getClone();
	
	Capture newCapture(String id, int start, Integer end);
	Result newResult(Rule rule);
}
