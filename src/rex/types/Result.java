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
	
	public List<Result> getChildren(){
		if(this.children == null) this.children = new ArrayList<Result>();
		return this.children;
	}
	
	public Result getFirst() {
		return getFirstFrom(this.children);
	}
	
	public Result getLast() {
		return getLastFrom(this.children);
	}
	
	public Result add(Result child) {
		this.getChildren().add(child);
		return this;
	}
	
	public Result add(String id, Span value) {
		this.getVars(id).add(value);
		return this;
	}
	
	public Span getVar(String id) {
		return this.getLast(id);
	}
	
	public Map<String, List<Span>> getVars(){
		if(this.vars == null) this.vars = new HashMap<String, List<Span>>();
		return this.vars;
	}
	
	public List<Span> getVars(String id){
		List<Span> list = this.getVars().get(id);
		if(list == null) this.vars.put(id, new ArrayList<Span>()); 
		return list;
	}
	
	public Span getFirst(String id) {
		if(this.vars == null) return null;
		return getFirstFrom(this.vars.get(id));
	}
	
	public Span getLast(String id) {
		if(this.vars == null) return null;
		return getLastFrom(this.vars.get(id));
	}
	
	private static <T> T getFirstFrom(List<T> list) {
		if(list == null || list.size() == 0) return null;
		return list.get(0);
	}

	private static <T> T getLastFrom(List<T> list) {
		if(list == null || list.size() == 0) return null;
		return list.get(list.size()-1);
	}
}
