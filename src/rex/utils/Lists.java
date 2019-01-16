package rex.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * provides List wrappers for common, non-list types (CharSequence aka String, arrays
 * and Iterator/Iterable
 */
public class Lists {

	
	public static List<Character> from(final CharSequence chars){
		final int count = chars.length();
		return new AbstractList<Character>() {
			@Override
			public Character get(int index) {
				index = Convert.getIndex(index, count);
				if(index < 0 || index > count) return null;
				return chars.charAt(index);
			}
			
			@Override
			public int size() {
				return count;
			}
		};
	}
	
	public static <T> List<T> from(final T[] array){
		final int count = array.length;
		return new AbstractList<T>() {
			@Override
			public T get(int index) {
				index = Convert.getIndex(index, count);
				if(index < 0 || index > count) return null;
				return array[index];
			}
			
			@Override
			public int size() {
				return count;
			}
		};
	}
	
	
	public static <T> List<T> from(final Iterable<T> source){
		List<T> list = (List<T>) source;
		if(list != null) return list;
		return from(source.iterator());
	}
	
	public static <T> List<T> from(final Iterator<T> it){
		if(it == null) throw new NullPointerException("it");
		
		return new AbstractList<T>() {
			private List<T> cache = new ArrayList<>();
			
			@Override
			public T get(int index) {
				if(index < 0) index = Convert.getIndex(index, size());
				if(index < 0) return null;
				int max = cache.size();
				if(index >= max) {
					while(it.hasNext()) {
						cache.add(it.next());
						max += 1;
						if(max > index) break;
					}
					if (index >= max) return null;
				}
				return cache.get(index);
			}
			
			@Override
			public int size() {
				int max = cache.size();
				while(get(max) != null) max += 1;
				return max;
			}
			
		};
	}
	
	
}
