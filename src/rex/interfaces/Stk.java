package rex.interfaces;

import rex.types.Node;

/***
 * singly linked list used in several parts of the siystem as a 
 * cloneable pseudo array
 */
public interface Stk<T> extends Lst<T> {
	T value();
	Stk<T> setValue(T value);
	T get();
	T get(int index);
	Stk<T> push(T value);
	T pop();
	T pop(int index);
	T peek();
	T peek(int index);
	T swap(T newValue);
	Node<T> getNode();
	Stk<T> getClone();
}
