package rex.types;

import java.lang.reflect.Array;
import java.util.ArrayList;

import rex.interfaces.Writer;

public class ArrayWriter<T> implements Writer<T[], T> {

	
	private ArrayList<T> result = new ArrayList<T>();
	private Class<T> refClass;
	
	public ArrayWriter(Class<T> refClass) {
		if(refClass == null) throw new NullPointerException("refClass");
		this.refClass = refClass;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayWriter(T[] ref) {
		this((Class<T>) ref.getClass().getComponentType());
	}
	
	@Override
	public Writer<T[], T> write(Iterable<T> values) {
		for(T value: values) result.add(value);
		return this;
	}

	@Override
	public Writer<T[], T> append(T value) {
		result.add(value);
		return this;
	}
	
	@Override
	public T[] getValue() {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) Array.newInstance(refClass, 0);
		return result.toArray(arr);
	}

}
