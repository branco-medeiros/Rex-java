package rex.types;

public interface Tracker<T> {
	boolean track(T value, int index);
}
