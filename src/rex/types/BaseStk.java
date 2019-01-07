package rex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rex.interfaces.Predicate;
import rex.interfaces.Range;
import rex.interfaces.Spn;
import rex.interfaces.Stk;
import rex.utils.Create;

public abstract class BaseStk<T> implements Stk<T>{
	
	private Node<T> node;
	
	public BaseStk() {
		//this.node = new Node<T>(null, null);
	}
	
	public BaseStk(Stk<T> other) {
		if(other == null) throw new NullPointerException("other");
		Node<T> n = other.getNode();
		this.node = n == null? null: new Node<T>(n.value, n.prev);
	}
	
	protected int getIndex(int index) {
		return index < 0? count() + index: index;
	}
	
	
	@Override
	public T value() {
		return node == null? null: node.value;
	}

	protected Node<T> theNode(){
		if(node == null) node = new Node<T>(null, null);
		return node;
	}
	
	@Override
	public Stk<T> setValue(T value) {
		theNode().value = value;
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
		node = new Node<T>(value, node);
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
		node = n.prev;
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
	public T swap(T newValue) {
		T prev = this.value();
		this.setValue(newValue);
		return prev;
	}

	@Override
	public Integer count() {
		return node == null? 0: node.index + 1;
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
	public Spn<T> span(Range range) {
		return span(range.start(), range.end());
	}
	

	@Override
	public Spn<T> slice(int start, int count) {
		start = getIndex(start);
		return span(start, start + count);
	}

	@Override
	public List<T> toList() {
		List<T> ret = new ArrayList<T>(count());
		Node<T> cur = node;
		while(cur != null) {
			ret.set(cur.index, cur.value);
			cur = cur.prev;
		}
		return ret;
	}

	@Override
	public Object[] toArray() {
		return this.toList().toArray();
	}
	
	@Override
	public T[] toArray(T[] ref) {
		return this.toList().toArray(ref);
	}

	@Override
	public Iterator<T> iterator() {
		return new StkIterator<T>(node);
	}

	@Override
	public Node<T> getNode() {
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
		while(cur != null) {
			if(cur.index == index) break;
			cur = cur.prev;
		}
		return cur;
	}
	
	public Stk<T> clone(){
		throw new UnsupportedOperationException();
	}
	
}
