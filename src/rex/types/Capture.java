package rex.types;

import java.util.List;

import rex.Context;
import rex.interfaces.Lst;
import rex.interfaces.Range;
import rex.interfaces.Spn;
import rex.utils.Create;

public class Capture implements Range{

	private String id;
	private int start;
	private Integer end;
	private Context ctx;
	
	public Capture(String id, Context ctx, int start, Integer end) {
		if(ctx == null) throw new NullPointerException("ctx");
		this.id = id == null? "": id;
		this.start = start;
		this.end = end;
		this.ctx = ctx;
	}

	public Capture(String id, Context ctx) {
		this(id, ctx, ctx.position(), null);
	}

	public Capture(Capture other) {
		if(other == null) throw new NullPointerException("other");
		this.id = other.id;
		this.ctx = other.ctx;
		this.start = other.start;
		this.end = other.end == null? null: other.end.intValue();
	}

	public Context ctx() {
		return this.ctx;
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

	public <T> Spn<T> span(Lst<T> src){
		return Create.spnFrom(src, this.start, this.end);
	}

	public <T> Spn<T> span(List<T> src){
		return Create.spnFrom(src, this.start, this.end);
	}

	public <T> Spn<T> span(T[] src){
		return Create.spnFrom(src, this.start, this.end);
	}

	public Spn<Character> span(CharSequence src){
		return Create.spnFrom(src, this.start, this.end);
	}

	public Capture getClone() {
		return new Capture(this);
	}

}
