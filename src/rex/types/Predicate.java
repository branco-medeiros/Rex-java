package rex.types;

public interface Predicate<T> {
	Boolean eval(T value, int index);
}
