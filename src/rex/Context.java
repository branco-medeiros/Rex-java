package rex;

import java.util.List;

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


	ParseResult result();
	ParseResult enter(Rule rule);
	ParseResult leave(boolean Result);
	
	Iterable<?> range(int start, Integer end);
	
	Context getClone();
	
	Match find(Matcher matcher);
	List<Match> findAll(Matcher matcher);
	
	Context eval(Matcher matcher);
	
}
