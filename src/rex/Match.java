package rex;

import java.util.List;

import rex.interfaces.Range;
import rex.types.Capture;
import rex.types.ParseResult;

public interface Match extends Iterable<Match>, Range {
	boolean matched();
	ParseResult result();
	List<Capture> group(String id);
	Capture capture(String id);
	Match next();
}
