package rex.other;

import java.util.NoSuchElementException;

public abstract class SeqBase<T> extends LstBase<T> implements Seq<T> {

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
	public T moveNext() {
		T cur = current();
		if(pos < 0 || cur != null) pos += 1;
		return cur;
	}

	@Override
	public T next() {
		if(finished()) throw new NoSuchElementException();
		return moveNext();
	}


	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
