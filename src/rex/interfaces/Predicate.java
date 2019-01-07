package rex.interfaces;

public interface Predicate<T> {
	Boolean eval(T value, int index);
}
