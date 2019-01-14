package rex.types;

import java.util.Iterator;
import java.util.NoSuchElementException;

import rex.Match;

public class MatchIterator implements Iterator<Match>{

	private Match current;
	
	public MatchIterator(Match current) {
		this.current = current;
	}
	
	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Match next() {
		if(current == null) throw new NoSuchElementException();
		Match temp = current;
		current = temp.next();
		return temp;
	}

}
