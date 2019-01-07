package rex.interfaces;

import java.util.Iterator;

public interface Seq<T> extends Lst<T>, Iterator<T> {
	Seq<T> setPosition(int value);
	int position();
	T current();
	
	boolean finished();
	
	//returns !finished()
	boolean hasNext();
	
	boolean moveNext();
	
}
