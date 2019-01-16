package rex.writers;

import java.util.ArrayList;
import java.util.List;

import rex.interfaces.Writer;

public class ListWriter<T> implements Writer<List<T>, T> {
	
	private List<T> result = new ArrayList<T>();

	@Override
	public ListWriter<T> write(Iterable<T> values) {
		for(T value: values) result.add(value);
		return this;
	}

@Override
	public Writer<List<T>, T> append(T value) {
	result.add(value);
		return this;
	}	

	@Override
	public List<T> getValue() {
		return result;
	}
}
