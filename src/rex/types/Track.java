package rex.types;

/***
 * represents a linked list
 * pop and peek operations without index operate on the last item
 * of the list;
 * when an index is supplied, it simulates an array access, where 
 * index 0..n access the list from the oldest (0) to the newest (n)
 * and index -1..-n access the list from the newest (-1) to the oldest (-n)
 * 
 *  
 */


public class Track<T> {
	
	private Node<T> node;
	
	public T getValue() {
		return this.node.value;
	}
	
	public Track<T> setValue(T value){
		this.node.value = value;
		return this;
	}
	
	public int count() {
		return this.node.index + 1;
	}
	
	public T peek() {
		return this.node.value;
	}
	
	
	public T peek(int index) {
		index = this.mapIndex(index);
		if(index < 0 || index > this.node.index) return null;
		Node<T> n = this.trackNode(new Tracker<Node<T>>() {
			@Override
			public boolean track(Node<T> value, int index) {
				return value.index == index || value.index == 0;
			}
		});
		return n.value;
	}
	
	public T pop() {
		T result = this.node.value;
		if(this.node.prev != null) {
			this.node = this.node.prev;
		} else {
			this.node.value = null;
		}
		return result;
	}
	
	
	public T pop(int index) {
		index = this.mapIndex(index);
		if(index < 0 || index > this.node.index) return null;
		this.node = this.trackNode(new Tracker<Node<T>>() {
			@Override
			public boolean track(Node<T> value, int index) {
				return value.index == index || value.index == 0;
			}
		});
		return this.pop();
	}
	
	public Track<T> push(T value){
		this.node = new Node<T>(value, this.node);
		return this;
	}
	
	public void each(Tracker<T> tracker) {
		this.trackNodeValue(tracker);
	}
	
	public T find(Tracker<T> tracker) {
		Node<T> n = this.trackNodeValue(tracker);
		return n == null? null: n.value;
	}
	
	public Integer findIndex(Tracker<T> tracker) {
		Node<T> n = this.trackNodeValue(tracker);
		return n == null? null: n.index;
	}
	
	private int mapIndex(int index) {
		if(index < 0) return this.node.index + index - 1;
		return index;
	}
	
	private Node<T> trackNodeValue(Tracker<T> tracker){
		final Tracker<T> t = tracker;
		return this.trackNode(new Tracker<Node<T>>() {
			@Override
			public boolean track(Node<T> value, int index) {
				return t.track(value.value, value.index);
			}
		});
	}
	
	private Node<T> trackNode(Tracker<Node<T>> tracker){
		Node<T> cur = this.node;
		int index = 0;
		while(cur != null) {
			if(tracker.track(cur,  index)) return cur;
			cur = cur.prev;
			index +=1;
		}
		return null;
	}
	
	
}
