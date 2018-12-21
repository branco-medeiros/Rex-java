package rex.types;

public interface Spn<T> extends Lst<T>, Range {
	Lst<T> source();
}
