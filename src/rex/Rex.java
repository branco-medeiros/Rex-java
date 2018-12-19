package rex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import rex.matchers.AndMatcher;
import rex.matchers.AnyMatcher;
import rex.matchers.EpsMatcher;
import rex.matchers.LitMatcher;
import rex.matchers.OrMatcher;
import rex.matchers.RepMatcher;

public class Rex {
	
	@SuppressWarnings("rawtypes")
	public static Matcher asMatcher(Object value) {
		if(value == null) throw new NullPointerException("value");
		if(value instanceof Matcher) return (Matcher) value;
		if(value instanceof Iterable && !(value instanceof String)) {
			List<Matcher> list = new ArrayList<Matcher>();
			Iterator it = ((Iterable) value).iterator();
			while(it.hasNext()) list.add(asMatcher(it.next()));
			return new AndMatcher(list);
		}
		
		return new LitMatcher(value);
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Matcher> asMatcherList(Iterable value) {
		List<Matcher> list = new ArrayList<Matcher>();
		Iterator it = ((Iterable) value).iterator();
		while(it.hasNext()) list.add(asMatcher(it.next()));
		return list;
	}
	
	public static LitMatcher lit(Object value) {
		return new LitMatcher(value);
	}
	
	public static AndMatcher all(Object... values) {
		return new AndMatcher(asMatcherList(Arrays.asList(values)));
	}
	
	public static OrMatcher first(Object... values) {
		return new OrMatcher(asMatcherList(Arrays.asList(values)));
	}
	
	public static RepMatcher rep(Integer min, Integer max, Object value) {
		return new RepMatcher(min, max, asMatcher(value));
	}
	
	public static RepMatcher plus(Object... values) {
		Matcher v = values.length == 1? asMatcher(values[0]): all(values);
		return new RepMatcher(1, null, v);
	}
	
	public static RepMatcher star(Object... values) {
		Matcher v = values.length == 1? asMatcher(values[0]): all(values);
		return new RepMatcher(null, null, v);
	}
	
	public static RepMatcher opt(Object... values) {
		Matcher v = values.length == 1? asMatcher(values[0]): all(values);
		return new RepMatcher(null, 1, v);
	}
	
	
	
	public static AnyMatcher any() {
		return new AnyMatcher();
	}
	
	public static EpsMatcher eps() {
		return new EpsMatcher();
	}
	
	
	
}
