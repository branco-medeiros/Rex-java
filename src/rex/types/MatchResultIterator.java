package rex.types;

import java.util.Iterator;

public class MatchResultIterator<T> implements Iterator<MatchResult<T>>{

	private MatchResult<T> current;
	
	public MatchResultIterator(MatchResult<T> current) {
		this.current = current;
	}
	
	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public MatchResult<T> next() {
		MatchResult<T> temp = current;
		current = temp.next();
		return temp;
	}

}
