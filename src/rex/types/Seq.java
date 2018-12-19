package rex.types;

import java.util.Iterator;
import java.util.List;

public interface Seq<T> {
	T get(int index);
	int count();
	List<T> toList();
	T[] toArray();
	Iterator<T> iterator();
}
