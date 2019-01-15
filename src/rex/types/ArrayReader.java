package rex.types;

public class ArrayReader<T> extends BaseReader<T> {

	private T[] source;
	
	public ArrayReader(T[] source) {
		if(source == null) throw new NullPointerException("source");
		this.source = source;
	}
	
	T[] source() {
		return source;
	}
	
	@Override
	public int count() {
		return source.length;
	}

	@Override
	protected T getAt(int index) {
		if(index < 0 || index >= source.length) return null;
		return source[index];
	}

}
