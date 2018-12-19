package rex.other;

public class Node<T> {
	public Node<T> prev;
	public T value;
	public int index;
	
	public Node(T value, Node<T> prev) {
		this.value = value;
		this.prev = prev;
		if(prev != null) this.index = this.prev.index + 1; 
	}
}
