package rex.types;

import java.util.Iterator;

import rex.interfaces.Reader;
import rex.utils.Types;

public abstract class BaseReader<T> implements Reader<T> {

	protected abstract T getAt(int index);
	
	public int getIndex(int value) {
		return value >= 0? value: Types.getIndex(value, count());
	}
	
	@Override
	public T get(int index) {
		return getAt(getIndex(index));
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ReaderIterator<>(this);
	}

	@Override
	public boolean canGet(int index) {
		return index < count();
	}
	
}
