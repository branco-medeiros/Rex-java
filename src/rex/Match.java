package rex;

import java.util.List;

import rex.interfaces.Capture;
import rex.interfaces.Range;
import rex.interfaces.Result;

/***
 * returned from rex.find; can be used interactively to continue the search;
 * - matched(): returns true or false according to the parsing result;
 * - result(): the parsing result(the rule hierachy and captures produced during parsing)
 * - next(): returns a new MatchResult of the appropriate type executing the last operation again
 * 			on the subject at the current position;
 * - start(): returns the starting position of the match
 * - end(): returns the ending position (if any) of the match
 * - group(id): returns a list with all captures matching id
 * - capture(id): returns the last occurrence of the the capture matching id
 * - value(): returns a span over the capture matching id
 * - iterator(): returns an iterator for matchresults starting with the current one;
 *
 * Usage:
 * 	//as an enumerable
 *  for(MatchResult<Character> m: rex.find(matcher, SomeText)){
 *  	System.out.println(m.span().toString())  	
 *	}
 */
public interface Match extends Iterable<Match>, Range {
	boolean matched();
	Result result();
	List<Capture> group(String id);
	Capture capture(String id);
	Match next();
	List<?> value();
}
