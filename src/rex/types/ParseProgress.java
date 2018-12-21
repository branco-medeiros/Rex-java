package rex.types;

import rex.matchers.Rule;

public class ParseProgress {
	private Rule rule;
	private int position;
	
	public Rule rule() {
		return rule;
	}
	
	public int position() {
		return position;
	}
	
	public ParseProgress setRule(Rule value) {
		this.rule = value;
		return this;
	}
	
	public ParseProgress setPosition(int value) {
		this.position = value;
		return this;
	}
}
