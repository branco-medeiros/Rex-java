package rex.other;

public class Capture<T> extends SpnT<T>{

	private String id;
	
	public Capture(String id, Lst<T> src, int start, Integer end) {
		super(src, start, end);
		this.id = id == null? "": id;
	}

	public Capture(String id, Seq<T> seq) {
		this(id, seq, seq.position(), null);
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
	
	
}
