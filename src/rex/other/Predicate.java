package rex.other;

public interface Predicate<T> {
	Boolean eval(T value, int index);
}