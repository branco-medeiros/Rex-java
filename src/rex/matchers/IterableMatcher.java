package rex.matchers;

public abstract class IterableMatcher extends MatcherClass {

	protected Iterable<?> items;
	
	public IterableMatcher(Iterable<?> items) {
		if(items == null) throw new NullPointerException("items");
		this.items = items;
	}
	
	public Iterable<?> items() {
		return this.items;
	}
	
}
