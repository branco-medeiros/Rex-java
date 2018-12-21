package rex.types;

import java.util.List;

import rex.matchers.Rule;

public interface Context {
	boolean finished();
	boolean moveNext();
	int position();
	Context setPosition(int value);
	boolean matches(int position, Object value);
	boolean matches(Object value);

	ParseResult result();
	ParseResult root();
	ParseResult enter(Rule rule);
	ParseResult leave(boolean Result);
	ParseResult swap(ParseResult other);
	List<ParseResult> trace();
	ParseResult find(Predicate<ParseResult> fn);
}
