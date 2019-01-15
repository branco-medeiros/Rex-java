package rex.types;

import rex.interfaces.Reader;

public class Iter<T> extends ReaderIterator<T>{
	private Reader<T> source;
	private int position;
	
	public Iter(Reader<T> source) {
		super(source);
		position = 0;
	}
	
	public Iter(Iter<T> other) {
		super(other.source());
		this.position = other.position();
	}
	
	public Reader<T> source(){
		return this.source;
	}
	
	public int position() {
		return this.position;
	}
	
	public Iter<T> position(int value){
		this.position = value;
		return this;
	}
	
	public boolean finished() {
		return !source.canGet(position);
	}
	
	public Iter<T> moveNext(){
		if(finished()) return null;
		this.position += 1;
		return this;
	}
	
	public Iter<T> getClone(){
		return new Iter<>(this);
	}
	
	
}
