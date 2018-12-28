package rex.types;

public class Capture implements Range{

	private String id;
	private int start;
	private Integer end;
	
	public Capture(String id, int start, Integer end) {
		this.id = id == null? "": id;
		this.start = start;
		this.end = end;
	}

	public Capture(String id, Context ctx) {
		this(id, ctx.position(), null);
	}
	
	public String id() {
		return this.id;
	}

	public boolean matches(String id) {
		return (id == null? "": id).equals(this.id());
	}
	
	public boolean matchesIgnoreCase(String id) {
		return (id == null? "": id).equalsIgnoreCase(this.id());
	}
	
	@Override
	public int start() {
		return start;
	}

	public Capture setStart(int value) {
		this.start = value;
		return this;
	}
	
	@Override
	public Integer end() {
		return end;
	}
	
	public Capture setEnd(Integer value) {
		this.end = value;
		return this;
	}
	
	@Override
	public String toString() {
		return Capture.toString(this);
	}

	public static String toString(Capture c) {
		if(c == null) return "[<NULL>]";
		Integer end = c.end();
		return String.format("[%s: %d, %s]", c.id(), c.start(), (end == null? "?": end.toString()));
	}
	
}
