package rex.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rex.Matcher;
import rex.interfaces.Lst;
import rex.interfaces.MatchAction;
import rex.types.MatchResult;
import rex.types.TContext;

/***
 * Provides static methods which perform the actions exposed by rex, namely 
 * @author branco.medeiros
 *
 */
public class Match {

	
	private interface Content<T>{
		Iterable<T> get(MatchResult<T> src);
	}
	
	private static class ValueContent<T> implements Content<T>{
		private Iterable<T> value;
		
		public ValueContent<T> value(Iterable<T> value){
			this.value = value;
			return this;
		}
		
		@Override
		public Iterable<T> get(MatchResult<T> src) {
			return value;
		}

		
	}
	 
	private static class CapContent<T> implements Content<T>{
		private String id;

		public CapContent<T> id(String value){
			this.id = value;
			return this;
		}
		
		@Override
		public Iterable<T> get(MatchResult<T> src) {
			return src.span(id);
		}
		
	}
	
	private interface Builder<T>{
		void append(T value);
	}
	
	public static <T> MatchResult<T> find(Lst<T> src, Matcher m){
		MatchAction<T> action = new MatchAction<T>() {
			@Override
			public MatchResult<T> eval(TContext<T> ctx) {
				while(!ctx.finished()) {
					int pos = ctx.position();
					if(m.match(ctx)) {
						return new MatchResult<T>(this, ctx, true, pos);
					}
					ctx.setPosition(pos).moveNext();
				}
				
				return new MatchResult<T>(this, ctx, false, ctx.position());
			}
		};
		
		return action.eval(Create.contextFrom(src));
	}
	
	public static <T> List<MatchResult<T>> findAll(Lst<T> src, Matcher m){
		List<MatchResult<T>> result = new ArrayList<MatchResult<T>>();
		for(MatchResult<T> mr: find(src, m)) {
			result.add(mr);
		}
		return result;
	}
	
	
	public static <T> Lst<T> replace(Lst<T> src, Matcher m, Iterable<?> args){
		return buildReplace(src, m, true, args);
	}
	
	public static String replace(String src, Matcher m, Iterable<?> args){
		return buildReplace(src, m, true, args);
	}
	
	public static <T> Lst<T> replaceAll(Lst<T> src, Matcher m, Iterable<?> args){
		return buildReplace(src, m, false, args);
	}
	
	public static String replaceAll(String src, Matcher m, Iterable<?> args){
		return buildReplace(src, m, false, args);
	}
	
	
	private static <T> Lst<T> buildReplace(Lst<T> src, Matcher m, boolean firstOnly, Iterable<?> args){
		List<T> result = new ArrayList<T>();
		Builder<T> builder = new Builder<T>() {
			@Override
			public void append(T value) {
				result.add(value);
			}
		};
		return doReplace(src, builder, m, getContent(args), firstOnly)
			? src
			: Create.lstFrom(result);
	}
	
	private static String buildReplace(String src, Matcher m, boolean firstOnly, Iterable<?> args) {
		StringBuilder result = new StringBuilder();
		Builder<Character> builder = new Builder<Character>() {
			@Override
			public void append(Character value) {
				result.append(value);
			}
		};
		return doReplace(Create.lstFrom(src), builder, m, getContent(args), firstOnly)
			? src
			: result.toString();
		
	}
	
	private static <T> boolean doReplace(
		Lst<T> src, 
		Builder<T> result, 
		Matcher matcher, 
		List<Content<T>> rep,
		boolean firstOnly
	){
		List<MatchResult<T>> found = firstOnly
				? Arrays.asList(find(src, matcher))
				: findAll(src, matcher);
		if(found.size() == 0 || (found.size() == 1 &&  !found.get(0).matched())) return false;

		int lastPos = 0;
		for(MatchResult<T> m: found) {
			if(!m.matched()) continue;
			int start = m.start();
			for(int i= lastPos; i < start; ++i) {
				result.append(src.get(i));
			}
			for(Content<T> content: rep) {
				for(T item: content.get(m)) result.append(item);
			}
			lastPos = m.end();
		}
		int end = src.count();
		for(int i = lastPos; i < end; ++i) {
			result.append(src.get(i));
		}
		
		return true;
	}
	

	/***
	 * converts the supplied list of elements into a list of spans
	 * the elements may be of type T proper (in which case they will be converted into
	 * an Iterator<T>) or of type string, in which case they will represent a named
	 * capture for the content that will be provided later
	 */
	private static <T> List<Content<T>> getContent(Iterable<?> iter){
		List<Content<T>> result = new ArrayList<Content<T>>();
		int t = 0;
		List<T> list = null;
		for(Object o: iter) {
			@SuppressWarnings("unchecked")
			T value = (T) o;
			if(value != null) {
				if(t != 1) {
					list = new ArrayList<T>();
					result.add(new ValueContent<T>().value(list));
					t = 1;
				}
				list.add(value);
				
			} else {
				t = 2;
				result.add(new CapContent<T>().id((String) o));
			}
		}
		return result;
		
	} //getContent<T>
	
}
