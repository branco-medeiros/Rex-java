package rex;

import java.util.List;

import rex.interfaces.Capture;
import rex.interfaces.Range;
import rex.interfaces.Result;

public interface Match extends Iterable<Match>, Range {
	boolean matched();
	Result result();
	List<Capture> group(String id);
	Capture capture(String id);
	Match next();
}
