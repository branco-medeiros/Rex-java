package rex.types;

import java.util.List;

public class ListReader<T> extends BaseReader<T> {

	private List<T> source;
	
	public ListReader(List<T> source) {
		if(source == null) throw new NullPointerException("source");
		this.source = source;
	}
	
	
	List<T> source(){
		return source;
	}
	
	@Override
	public int count() {
		return source.size();
	}

	@Override
	protected T getAt(int index) {
		return source.get(index);
	}

}
