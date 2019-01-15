package rex.interfaces;

public interface Reader<T> extends Iterable<T>{
	int count();
	T get(int index);
	boolean canGet(int index);
}
