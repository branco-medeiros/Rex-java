package rex;

import rex.interfaces.Stk;
import rex.matchers.Rule;
import rex.types.ParseResult;

public interface Context{
	boolean finished();
	boolean moveNext();
	int position();
	Context setPosition(int value);

	boolean matches(int position, Object value);
	boolean matches(Object value);
	boolean inRange(int position, Object first, Object last);
	boolean inRange(Object first, Object last);

	ParseResult root();
	ParseResult result();
	ParseResult enter(Rule rule);
	ParseResult leave(boolean Result);
	Stk<ParseResult> trace();
	
	Iterable<?> range(int start, Integer end);
	
	Context getClone();
	
}
