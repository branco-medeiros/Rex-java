package rex.interfaces;

/***
 * represents a generic sub list that works as a list also
 */
public interface Spn<T> extends Lst<T>, Range {
	Lst<T> source();
}
