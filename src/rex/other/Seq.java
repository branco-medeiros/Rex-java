package rex.other;

import java.util.Iterator;

public interface Seq<T> extends Lst<T>, Iterator<T> {
	Seq<T> setPosition(int value);
	int position();
	T current();
	
	boolean finished();
	
	//returns !finished()
	boolean hasNext();
	
	//returns the current item and advances to the next one
	T moveNext();
	
	//same as moveNext but throws if no more items
	//used to implement iterator
	T next();
}
