package rex.matchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grammar{

	private List<Rule> rules = new ArrayList<Rule>();
	private Map<String, Rule> dict = new HashMap<String, Rule>();
	private Rule defaultRule;
	
	public Rule get() {
		return defaultRule;
	}
	
	public Rule get(String id) {
		String key = keyFrom(id);
		Rule r = dict.get(key);
		if(r == null) {
			r = createRule(id);
			rules.add(r);
			dict.put(key,  r);
		}
		
		if(defaultRule == null) defaultRule = r;
		return r;
	}
	
	public List<Rule> rules(){
		return this.rules;
	}
	
	public Map<String, Rule> dict(){
		return this.dict;
	}
	
	public Rule defaultRule() {
		return this.defaultRule;
	}
	
	public Grammar setDefaultRule(Rule value) {
		this.defaultRule = value;
		return this;
	}
	
	protected Rule createRule(String id) {
		return new Rule(id);
	}
	
	protected String keyFrom(String id) {
		if(id == null) throw new NullPointerException("id");
		return id.toLowerCase();
	}
}
