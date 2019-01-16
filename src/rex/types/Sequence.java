package rex.types;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import rex.utils.Convert;

public class Sequence<T> extends AbstractList<T> implements Iterator<T>{
	
	private List<T> source;
	private int position;
	
	public Sequence(List<T> source) {
		if(source == null) throw new NullPointerException("source");
		this.source = source;
		position = 0;
	}
	
	public Sequence(Sequence<T> other) {
		this(other.source());
		this.position = other.position();
	}
	
	public List<T> source(){
		return this.source;
	}
	
	public T current() {
		return get(position);
	}
	
	public int position() {
		return this.position;
	}
	
	public Sequence<T> position(int value){
		this.position = value;
		return this;
	}
	
	public boolean finished() {
		return position >= 0 && source.get(position) == null;
	}
	
	public boolean moveNext(){
		if(finished()) return false;
		this.position += 1;
		return true;
	}
	
	public Sequence<T> getClone(){
		return new Sequence<>(this);
	}

	@Override
	public boolean hasNext() {
		return !finished();
	}

	@Override
	public T next() {
		T ret = current();
		if(!moveNext()) throw new NoSuchElementException(); 
		return ret;
	}

	@Override
	public T get(int index) {
		if(index < 0) index = Convert.getIndex(index, size());
		return source.get(index);
	}

	@Override
	public int size() {
		return source.size();
	}
	
	
}
