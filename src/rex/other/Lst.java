package rex.other;

import java.util.List;

public interface Lst<T> extends Iterable<T>{
	T get(int index);
	Integer count();
	T each(Predicate<T> fn);
	Spn<T> span(int start, Integer end);
	Spn<T> slice(int start, int count);
	List<T> toList();
	T[] toArray();
}
