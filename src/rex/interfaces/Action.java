package rex.interfaces;

public interface Action<T, R> {
	R eval(T arg);
}
