package rex.interfaces;

/***
 * used by rex.replace to create the replaced result
 */
public interface Writer<L, T> {
	Writer<L, T> write(Iterable<T> values);
	L getValue();
}
