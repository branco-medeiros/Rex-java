package rex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorReader<T> extends BaseReader<T> {

	private List<T> cache;
	private Iterator<T> src;
	
	public IteratorReader(Iterable<T> src) {
		this(src.iterator());
	}
	
	public IteratorReader(Iterator<T> iterator) {
		if(iterator == null) throw new NullPointerException("iterator");
		cache = new ArrayList<T>();
		src = iterator;
	}
	
	Iterator<T> source(){
		return src;
	}
	

	@Override
	public boolean canGet(int index) {
		return getAt(index) != null;
	}
	
	@Override
	public int count() {
		int max = cache.size();
		while(get(max) != null) max += 1;
		return max;
	}

	@Override
	protected T getAt(int index) {
		if(index < 0) return null;
		int max = cache.size();
		if(index >= max) {
			while(src.hasNext()) {
				cache.add(src.next());
				max += 1;
				if(max > index) break;
			}
			if (index >= max) return null;
		}
		return cache.get(index);
	}

}
