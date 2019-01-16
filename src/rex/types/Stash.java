package rex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rex.interfaces.Predicate;
import rex.utils.Convert;

public class Stash<T> implements Iterable<T>{

	private Node<T> node;
	
	public Stash() {
	}
	
	public Stash(Stash<T> other) {
		this.node = other.getNode();
	}
	
	public Stash(T value) {
		push(value);
	}
	
	public Node<T> getNode() {
		return node;
	}

	public T value() {
		return node == null? null: node.value;
	}

	public Stash<T> value(T newValue){
		theNode().value = newValue;
		return this;
	}
	
	public Stash<T> push(T value) {
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
		return new StashIterator<>(node);
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
		int count = size();
		index = Convert.getIndex(index, count); if(index < 0 || index >= count) return null;
		Node<T> cur = node;
		while(cur.index > index) cur = cur.prev;
		return cur;
	}

	public T get(int index) {
		index = Convert.getIndex(index, size());
		Node<T> n = findNode(index);
		return n == null? null: n.value;
	}

	public int size() {
		return node == null? 0: node.index;
	}
	
	public Stash<T> getClone(){
		return new Stash<>(this);
	}
	
	public Stash<T> assign(Stash<T> other){
		if(other != null) this.node = other.node;
		return this;
	}
	
	public List<T> toList(){
		List<T> result = new ArrayList<>(size());
		Node<T> cur = node;
		while(cur != null) {
			result.set(cur.index, cur.value);
			cur = cur.prev;
		}
		return result;
	}

	

}
