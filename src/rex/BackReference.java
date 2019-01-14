package rex;

import java.util.Collections;
import java.util.Iterator;

/***
 * represents a back reference to a given capture; allows acessing the
 * actual capture that is referenced by its id
 */
public class BackReference<T> implements Iterable<T> {

	private String id;
	private Context ctx;
	
	public BackReference(String id) {
		this(id, null);
	}
	
	public BackReference(String id, Context ctx) {
		if(id == null) id = "";
		this.id = id;
		this.ctx = ctx;
	}
	
	public Iterable<T> from(Context ctx){
		return new BackReference<>(id, ctx);
	}
	
	@Override
	public Iterator<T> iterator() {
		return ctx == null? Collections.emptyIterator(): null;
	}
		
}
