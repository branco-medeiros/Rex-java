package rex;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import rex.interfaces.Lst;
import rex.interfaces.MatchAction;
import rex.interfaces.Writer;
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
import rex.types.ArrayWriter;
import rex.types.CharSequenceWriter;
import rex.types.ListWriter;
import rex.types.MatchResult;
import rex.utils.Create;

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
	
	public static FirstOfMatcher oneof(Iterable<?> source) {
		return new FirstOfMatcher(source);
	}
	
	public static SeqMatcher seq(Iterable<?> source) {
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

	public static <T> Context eval(Matcher matcher, Iterable<T> src) {
		Context ctx = Create.contextFrom(src);
		return eval(matcher, ctx);
	}
	
	public static <T> Context eval(Matcher matcher, Iterator<T> src) {
		Context ctx = Create.contextFrom(src);
		return eval(matcher, ctx);
	}
	
	public static Context eval(Matcher matcher, CharSequence src) {
		Context ctx = Create.contextFrom(src);
		return eval(matcher, ctx);
	}
	
	public static <T> Context eval(Matcher matcher, T[] src) {
		Context ctx = Create.contextFrom(src);
		return eval(matcher, ctx);
	}
	
	public static Context eval(Matcher matcher, Context ctx) {
		if(matcher == null) throw new NullPointerException("matcher");
		if(ctx == null) throw new NullPointerException("ctx");
		matcher.match(ctx);
		return ctx;
	}
	
	public static Match find(Matcher matcher, Context ctx) {
		MatchAction action = new MatchAction() {
			@Override
			public Match eval(Context ctx) {
				while(!ctx.finished()) {
					int pos = ctx.position();
					if(matcher.match(ctx)) {
						return new MatchResult(this, ctx, true, pos);
					}
					ctx.setPosition(pos).moveNext();
				}
				return new MatchResult(this, ctx, false, ctx.position());
			}
		};
		return action.eval(ctx);
	}
	
	public static List<Match> findAll(Matcher matcher, Context ctx){
		List<Match> result = new ArrayList<Match>();
		for(Match m: find(matcher, ctx)) {
			result.add(m);
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked") 
	public static CharSequence replace(
		Matcher matcher, 
		CharSequence chars, 
		Iterable<Character>... values
	) {
		return doReplace(
			chars,
			Create.contextFrom(chars), 
			new CharSequenceWriter(), 
			matcher, 
			true, 
			values
		);
	} //replace chars
	
	@SuppressWarnings("unchecked")
	public static <T> T[] replace(
		Matcher matcher,
		T[] itens,
		Iterable<T>... values
	) {
		Writer<T[], T> writer = new ArrayWriter<>(itens);
		boolean replaced = doReplace(
			Create.contextFrom(itens),
			writer,
			matcher,
			Arrays.asList(values),
			true
		);
		return replaced? writer.getValue(): itens;
	} //replace itens[]
			

	@SuppressWarnings("unchecked")
	public static <T> List<T> replace(
		Matcher matcher,
		List<T> list,
		Iterable<T>... values
	) {
		return doReplace(
			list,
			Create.contextFrom(list), 
			new ListWriter<>(), 
			matcher, 
			true, 
			values
		);
	} //replace list
			

	@SuppressWarnings("unchecked")
	public static <T> Iterable<T> replace(
		Matcher matcher,
		Iterable<T> iter,
		Iterable<T>... values
	) {
		Writer<List<T>, T> writer = new ListWriter<>();
		boolean replaced = doReplace(
			Create.contextFrom(iter),
			writer,
			matcher,
			Arrays.asList(values),
			true
		);
		return replaced? writer.getValue(): iter;
	} //replace iter
	
	
	@SuppressWarnings("unchecked") 
	public static CharSequence replaceAll(
		Matcher matcher, 
		CharSequence chars, 
		Iterable<Character>... values
	) {
		return doReplace(
			chars,
			Create.contextFrom(chars), 
			new CharSequenceWriter(), 
			matcher, 
			false, 
			values
		);
	} //replaceAll chars
	
	@SuppressWarnings("unchecked")
	public static <T> T[] replaceAll(
		Matcher matcher,
		T[] itens,
		Iterable<T>... values
	) {
		
		Writer<T[], T> writer = new ArrayWriter<>(itens);
		boolean replaced = doReplace(
			Create.contextFrom(itens),
			writer,
			matcher,
			Arrays.asList(values),
			false
		);
		return replaced? writer.getValue(): itens;
	} //replaceAll itens[]
			

	@SuppressWarnings("unchecked")
	public static <T> List<T> replaceAll(
		Matcher matcher,
		List<T> list,
		Iterable<T>... values
	) {
		return doReplace(
			list,
			Create.contextFrom(list), 
			new ListWriter<>(), 
			matcher, 
			false, 
			values
		);
	} //replaceAll list
			

	@SuppressWarnings("unchecked")
	public static <T> Iterable<T> replaceAll(
		Matcher matcher,
		Iterable<T> iter,
		Iterable<T>... values
	) {
		Writer<List<T>, T> writer = new ListWriter<>();
		boolean replaced = doReplace(
			Create.contextFrom(iter),
			writer,
			matcher,
			Arrays.asList(values),
			false
		);
		return replaced? writer.getValue(): iter;
	} //replaceAll iter
	
	@SuppressWarnings("unchecked")
	private static <L, T> L doReplace(
		L src, 
		Context ctx,
		Writer<L, T> writer, 
		Matcher matcher, 
		boolean firstOnly,
		Iterable<T>... values
	) {
		boolean replaced = doReplace(ctx, writer,  matcher, Arrays.asList(values), firstOnly);
		return replaced? writer.getValue(): src;
	}
	
	private static <L, T> boolean doReplace(
			Context src, 
			Writer<L, T> result, 
			Matcher matcher, 
			List<Iterable<T>> replaces,
			boolean firstOnly
		){
			//requires that source is convertible to Lst<T>
			@SuppressWarnings("unchecked")
			Lst<T> itens = (Lst<T>) src;
			if(itens == null) throw new RuntimeException(new InvalidClassException("source"));
			
			List<Match> found = firstOnly
					? Arrays.asList(find(matcher, src))
					: findAll(matcher, src);
					
			if(found.size() == 0 || (found.size() == 1 &&  !found.get(0).matched())) return false;

			int lastPos = 0;
			
			//adusts back references to actual captures
			for(int i=0, max = replaces.size(); i < max; ++i) {
				BackReference<T> br = (BackReference<T>) replaces.get(i);
				if(br != null) replaces.set(i, null); //todo: create a capture here
			}
			
			for(Match m: found) {
				if(!m.matched()) continue;
				int start = m.start();
				for(int i= lastPos; i < start; ++i) {
					result.append(itens.get(i));
				}
				
				for(Iterable<T> newValue: replaces) result.write(newValue);
				lastPos = m.end();
			}
			
			int end = itens.count();
			for(int i = lastPos; i < end; ++i) {
				result.append(itens.get(i));
			}
			
			return true;
		} //doReplace
			
	
}
