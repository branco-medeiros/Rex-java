package rex.types;

public class ParseResultState {
	int vars;
	int children;
	
	public ParseResultState setVars(int value) {
		vars = value;
		return this;
	}

	public ParseResultState setChildren(int value) {
		children = value;
		return this;
	}
}
