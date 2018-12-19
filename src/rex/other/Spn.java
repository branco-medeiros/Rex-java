package rex.other;

public interface Spn<T> extends Lst<T> {
	int start();
	Integer end();
	Lst<T> source();
}
