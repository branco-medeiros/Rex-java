package rex.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class StkBase<T> implements  Stk<T>{
	
	private Node<T> node;
	
	public StkBase() {
		this.node = new Node<T>(null, null);
	}
	
	public StkBase(Stk<T> other) {
		if(other == null) throw new NullPointerException("other");
		Node<T> n = other.getNode();
		this.node = new Node<T>(n.value, n.prev);
	}
	
	protected int getIndex(int index) {
		return index < 0? count() + index: index;
	}
	
	
	@Override
	public T value() {
		return node.value;
	}

	@Override
	public Stk<T> setValue(T value) {
		node.value = value;
		return this;
	}

	@Override
	public T get() {
		return value();
	}

	@Override
	public T get(int index) {
		return peek(index);
	}

	@Override
	public Stk<T> push(T value) {
		if(node.value == null) {
			node.value = value;
		} else {
			node = new Node<T>(value, node);
		}
		return this;
	}

	@Override
	public T pop() {
		return pop(-1);
	}

	@Override
	public T pop(int index) {
		Node<T> n = findNode(index);
		if(n == null) return null;
		T ret = n.value;
		if(n.prev == null) {
			node = n;
			n.value = null;
		} else {
			node = n.prev;
		}
		return ret;
	}

	@Override
	public T peek() {
		return value();
	}

	@Override
	public T peek(int index) {
		Node<T> n = findNode(index);
		return n == null? null: n.value;
	}

	@Override
	public Integer count() {
		return node.index + 1;
	}

	@Override
	public T each(Predicate<T> fn) {
		Node<T> n = eachNode(fn);
		return n == null? null: n.value;
	}

	@Override
	public Spn<T> span(int start, Integer end) {
		start = getIndex(start);
		end = end == null? count(): getIndex(end);
		return Create.spnFrom(this, start, end);
	}

	@Override
	public Spn<T> slice(int start, int count) {
		start = getIndex(start);
		return span(start, start + count);
	}

	@Override
	public List<T> toList() {
		return Arrays.asList(this.toArray());
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] ret = (T[]) new Object[count()];
		Node<T> cur = node;
		while(cur != null) {
			ret[cur.index] = cur.value;
			cur = cur.prev;
		}
		return ret;
	}

	@Override
	public Iterator<T> iterator() {
		return new StkIter<T>(node);
	}

	@Override
	public Node<T> getNode() {
		return node;
	}
	
	protected Node<T> eachNode(Predicate<T> fn){
		Node<T> cur = node;
		while(cur != null) {
			if(fn.eval(cur.value,  cur.index)) break;
			cur = cur.prev;
		}
		return cur;
	}
	
	protected Node<T> findNode(int index){
		index = getIndex(index); if(index < 0 || index >= count()) return null;
		Node<T> cur = node;
		while(cur != null) {
			if(cur.index == index) break;
			cur = cur.prev;
		}
		return cur;
	}
	
	
	
}
