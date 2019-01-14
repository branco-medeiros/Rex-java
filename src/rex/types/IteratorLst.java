package rex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorLst<T> extends BaseLst<T> {

	private List<T> cache;
	private Iterator<T> src;
	
	public IteratorLst(Iterable<T> src) {
		this(src.iterator());
	}
	
	public IteratorLst(Iterator<T> iterator) {
		if(iterator == null) throw new NullPointerException("iterator");
		cache = new ArrayList<T>();
		src = iterator;
	}

	@Override
	protected T getAt(int index) {
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

	@Override
	public Integer count() {
		int max = cache.size();
		while(get(max) != null) max += 1;
		return max;
	}
	
	

}
