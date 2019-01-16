package rex.types;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StashIterator<T> implements Iterator<T> {

	private Node<T> node;
	
	public StashIterator(Node<T> node) {
		this.node = node;
	}
	
	@Override
	public boolean hasNext() {
		return node != null;
	}

	@Override
	public T next() {
		if(node == null) throw new NoSuchElementException();
		T ret = node.value;
		node = node.prev;
		return ret;
	}

}
