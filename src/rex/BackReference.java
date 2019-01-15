package rex;

import java.util.Collections;
import java.util.Iterator;

/***
 * represents a back reference to a given capture; allows acessing the
 * actual capture that is referenced by its id
 */
public class BackReference<T> implements Iterable<T> {

	private String id;
	
	public BackReference(String id) {
		this.id = id;
	}
	
	public String id() {
		return id;
	}
	
	@Override
	public Iterator<T> iterator() {
		return Collections.emptyIterator();
	}
		
}
