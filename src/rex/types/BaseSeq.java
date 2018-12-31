package rex.types;

import java.util.NoSuchElementException;

import rex.interfaces.Seq;

public abstract class BaseSeq<T> extends BaseLst<T> implements Seq<T> {

	protected int pos;

	@Override
	public Seq<T> setPosition(int value) {
		pos = value;
		return this;
	}

	@Override
	public int position() {
		return pos;
	}

	@Override
	public boolean finished() {
		return pos >= 0 && current() == null;
	}

	@Override
	public boolean hasNext() {
		return !finished();
	}

	
	@Override
	public boolean moveNext() {
		if(finished()) return false;
		pos += 1;
		return true;
	}

	@Override
	public T next() {
		T ret = current();
		if(!moveNext()) throw new NoSuchElementException();
		return ret;
	}


	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
