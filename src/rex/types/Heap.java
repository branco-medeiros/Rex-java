package rex.types;

import java.util.Iterator;

import rex.interfaces.Predicate;

public class Heap<T> extends BaseReader<T>{

	private Node<T> node;
	
	public Node<T> getNode() {
		return node;
	}

	@Override
	public int count() {
		return node == null? 0: node.index + 1;
	}

	@Override
	protected T getAt(int index) {
		if(index < 0 || index >= count()) return null;
		Node<T> n = findNode(index);
		return n == null? null: n.value;
	}
	
	public T value() {
		return node == null? null: node.value;
	}

	public Heap<T> value(T newValue){
		theNode().value = newValue;
		return this;
	}
	
	public Heap<T> push(T value) {
		node = new Node<T>(value, node);
		return this;
	}

	public T pop() {
		return pop(-1);
	}

	public T pop(int index) {
		Node<T> n = findNode(index);
		if(n == null) return null;
		T ret = n.value;
		node = n.prev;
		return ret;
	}
	
	public T peek() {
		return value();
	}

	public T peek(int index) {
		Node<T> n = findNode(index);
		return n == null? null: n.value;
	}

	public T swap(T newValue) {
		T prev = this.value();
		this.value(newValue);
		return prev;
	}

	public T each(Predicate<T> fn) {
		Node<T> n = eachNode(fn);
		return n == null? null: n.value;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new HeapIterator<>(node);
	}
	
	protected Node<T> theNode(){
		if(node == null) node = new Node<T>(null, null);
		return node;
	}
	
	protected Node<T> eachNode(Predicate<T> fn){
		Node<T> cur = node;
		while(cur != null) {
			boolean ok = cur.index > 0 || cur.value != null;
			if(ok) {
				if(fn.eval(cur.value,  cur.index)) break;
			}
			cur = cur.prev;
		}
		return cur;
	}

	protected Node<T> findNode(int index){
		index = getIndex(index); if(index < 0 || index >= count()) return null;
		Node<T> cur = node;
		while(cur.index > index) cur = cur.prev;
		return cur;
	}

	

}
