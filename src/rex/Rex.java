package rex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rex.interfaces.Context;
import rex.matchers.AndMatcher;
import rex.matchers.AnyMatcher;
import rex.matchers.CapMatcher;
import rex.matchers.EofMatcher;
import rex.matchers.EpsMatcher;
import rex.matchers.FirstOfMatcher;
import rex.matchers.Grammar;
import rex.matchers.IsMatcher;
import rex.matchers.IsNotMatcher;
import rex.matchers.LitMatcher;
import rex.matchers.OrMatcher;
import rex.matchers.PrecMatcher;
import rex.matchers.ReMatcher;
import rex.matchers.RepMatcher;
import rex.matchers.SeqMatcher;

public class Rex {
	
	@SuppressWarnings("rawtypes")
	public static Matcher asMatcher(Object value) {
		if(value == null) throw new NullPointerException("value");
		if(value instanceof Matcher) return (Matcher) value;

		if(value instanceof Object[]) {
			return flatten(asMatcherList((Object[]) value));
		}
		
		if(value instanceof Iterable && !(value instanceof String)) {
			return flatten(asMatcherList((Iterable) value));
		}
		
		return new LitMatcher(value);
	}
	
	public static Matcher flatten(List<Matcher> list) {
		return list.size() == 1? list.get(0): new AndMatcher(list);
	}
	
	public static List<Matcher> asMatcherList(Object[] values){
		List<Matcher> list = new ArrayList<Matcher>();
		for(Object o: values) {
			list.add(asMatcher(o));
		};
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Matcher> asMatcherList(Iterable values) {
		List<Matcher> list = new ArrayList<Matcher>();
		for(Object o: values){
			list.add(asMatcher(o));
		}
		return list;
	}

	
	public static LitMatcher lit(Object value) {
		return new LitMatcher(value);
	}
	
	public static AndMatcher and(Object... values) {
		return new AndMatcher(asMatcherList(Arrays.asList(values)));
	}
	
	public static OrMatcher or(Object... values) {
		return new OrMatcher(asMatcherList(Arrays.asList(values)));
	}
	
	public static RepMatcher rep(Integer min, Integer max, Object value) {
		return new RepMatcher(min, max, asMatcher(value));
	}
	
	public static RepMatcher plus(Object... values) {
		Matcher v = asMatcher(values);
		return new RepMatcher(1, null, v);
	}
	
	public static RepMatcher star(Object... values) {
		Matcher v = asMatcher(values);
		return new RepMatcher(null, null, v);
	}
	
	public static RepMatcher opt(Object... values) {
		Matcher v = asMatcher(values);
		return new RepMatcher(null, 1, v);
	}
	
	public static AnyMatcher any() {
		return new AnyMatcher();
	}
	
	public static EpsMatcher eps() {
		return new EpsMatcher();
	}
	
	public static EofMatcher eof() {
		return new EofMatcher();
	}
	
	@SuppressWarnings("rawtypes")
	public static FirstOfMatcher oneof(Iterable source) {
		return new FirstOfMatcher(source);
	}
	
	@SuppressWarnings("rawtypes")
	public static SeqMatcher seq(Iterable source) {
		return new SeqMatcher(source);
	}
	
	public static IsMatcher is(Object... values) {
		Matcher v = asMatcher(values);
		return new IsMatcher(v);
	}
	
	public static IsNotMatcher isnot(Object... values) {
		return new IsNotMatcher(asMatcher(values));
	}
	
	public static PrecMatcher prec(int prec, Object... values) {
		return new PrecMatcher(prec, asMatcher(values));
	}
	
	public static CapMatcher cap(String id, Object... values) {
		return new CapMatcher(id, asMatcher(values));
	}
	
	public static ReMatcher recap(String id) {
		return new ReMatcher(id);
	}
	
	public static Grammar grammar() {
		return new Grammar();
	}
	
	public static Context match(Matcher matcher, Context ctx) {
		if(matcher == null) throw new NullPointerException("matcher");
		if(ctx == null) throw new NullPointerException("ctx");
		matcher.match(ctx);
		return ctx;
	}
	
	public static Context find(Matcher matcher, Context ctx) {
		return null;
	}
	
	
}
