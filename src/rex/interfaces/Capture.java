package rex.interfaces;

import java.util.List;

public interface Capture extends Range{
	String id();
	Capture start(int value);
	Capture end(Integer value);
	boolean matches(CharSequence other);
	boolean matchesNoCase(CharSequence other);
	List<?> value();
}
