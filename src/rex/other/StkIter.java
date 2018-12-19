package rex.other;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StkIter<T>  implements Iterator<T>{

		private Node<T> cur;
		
		public StkIter(Node<T> node) {
			cur = node;
		}
		
		@Override
		public boolean hasNext() {
			return cur !=null;
		}

		@Override
		public T next() {
			if(cur == null) throw new NoSuchElementException();
			T ret = cur.value;
			cur = cur.prev;
			return ret;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

}
