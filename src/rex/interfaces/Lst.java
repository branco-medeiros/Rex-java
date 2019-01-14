package rex.interfaces;

import java.util.List;

public interface Lst<T> extends Iterable<T>{
	T get(int index);
	Integer count();
	T each(Predicate<T> fn);
	
	Iterable<T> range(Range limit);
	Iterable<T> range(int start, Integer end);
	
	Spn<T> span(Range range);
	Spn<T> span(int start, Integer end);
	
	Spn<T> slice(int start, int count);
	
	List<T> toList();
	Object[] toArray();
	T[] toArray(T[] ref);
	
	
}
