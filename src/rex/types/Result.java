package rex.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rex.matchers.Rule;

public class Result extends Span{
	private Rule rule;
	private List<Result> children;
	private Map<String, List<Span>> vars;
	
	public Rule getRule() {
		return this.rule;
	}
	
	public void setRule(Rule value) {
		this.rule = value;
	}

	public int count() {
		return this.children == null? 0: this.children.size();
	}
	
	public int count(String id) {
		List<Span> list = this.getVar(id);
		return list.size();
	}
	
	public Result push(Result value) {
		this.getChildren().add(value);
		return this;
	}
	
	public Result push(String id, Span value) {
		this.getVar(id).add(value);
		return this;
	}
	
	public Result put(int index, Result r) {
		put(this.getChildren(), index, r);
		return this;
	}
	
	public Result put(String id, int index, Span value) {
		put(this.getVar(id), index, value);
		return this;
	}
	
	public Result peek() {
		return peek(this.getChildren());
	}
	
	public Result peek(int index) {
		return peek(this.getChildren(), index);
	}
	
	public Result pop() {
		return pop(this.getChildren());
 	}
	
	public Result pop(int index) {
		return pop(this.getChildren(), index);
 	}
	
	public Span peek(String id) {
		return peek(this.getVar(id));
	}
	
	public Span peek(String id, int index) {
		return peek(this.getVar(id), index);
	}
	
	public Span pop(String id) {
		return pop(this.getVar(id));
	}
	
	public Span pop(String id, int index) {
		return pop(this.getVar(id), index);
	}
	
	public List<Result> getChildren(){
		if(this.children == null) this.children = new ArrayList<Result>();
		return this.children;
	}
	
	public List<Span> getVar(String id) {
		List<Span> list = this.getVars().get(id);
		if(list == null) this.vars.put(id, new ArrayList<Span>()); 
		return list;
	}
	
	public Map<String, List<Span>> getVars(){
		if(this.vars == null) this.vars = new HashMap<String, List<Span>>();
		return this.vars;
	}
	
	private static <T> int getIndex(List<T> list, int index) {
		if(index >= 0) return index;
		return list.size() + index;
	}

	private static <T> T peek(List<T> list) {
		int max = list.size() - 1;
		return list.get(max);
	}
	
	private static <T> T peek(List<T> list, int index) {
		index = getIndex(list, index);
		return list.get(index);
	}
	
	private static <T> T pop(List<T> list) {
		int max = list.size() - 1;
		T r = list.get(max);
		list.remove(max);
		return r;
	}
	
	private static <T> T pop(List<T> list, int index) {
		index = getIndex(list, index);
		if(index < 0 || index >= list.size()) return null;
		T r = list.get(index);
		list.subList(index,  list.size()).clear();
		return r;
	}
	
	private static <T> T put(List<T> list, int index, T value) {
		index = getIndex(list, index);
		if(index < 0 || index >= list.size()) return null;
		list.set(index, value);
		return value;
	}
	
}
