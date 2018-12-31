package rex.interfaces;

import java.util.List;

import rex.matchers.Rule;
import rex.types.Capture;
import rex.types.ParseResult;

public interface Context {
	boolean finished();
	boolean moveNext();
	int position();
	Context setPosition(int value);
	boolean matches(int position, Object value);
	boolean matches(Object value);

	ParseResult result();
	ParseResult root();
	Capture var(String id);
	ParseResult enter(Rule rule);
	ParseResult leave(boolean Result);
	ParseResult swap(ParseResult other);
	List<ParseResult> trace();
	ParseResult find(Predicate<ParseResult> fn);
	@SuppressWarnings("rawtypes")
	Iterable range(int start, Integer end);
}
