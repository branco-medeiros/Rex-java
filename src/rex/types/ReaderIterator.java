package rex.types;

import java.util.Iterator;
import java.util.NoSuchElementException;

import rex.interfaces.Reader;

public class ReaderIterator<T> implements Iterator<T> {

	private Reader<T> reader;
	private int index;
	
	public ReaderIterator(Reader<T> source) {
		if(reader == null) throw new NullPointerException("source");
		index = 0;
		reader = source;
	}

	@Override
	public boolean hasNext() {
		return reader.canGet(index);
	}

	@Override
	public T next() {
		if(!hasNext()) throw new NoSuchElementException();
		T ret = reader.get(index);
		index += 1;
		return ret;
	}

}
