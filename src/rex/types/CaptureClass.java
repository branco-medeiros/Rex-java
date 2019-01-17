package rex.types;

import java.util.List;

import rex.interfaces.Capture;
import rex.utils.Convert;

public class CaptureClass<T> implements Capture{

	private String id;
	private int start;
	private Integer end;
	private ContextClass<T> ctx;
	
	public CaptureClass(ContextClass<T> ctx, String id, int start, Integer end) {
		if(ctx == null) throw new NullPointerException("ctx");
		this.id = id == null? "": id;
		this.start = start;
		this.end = end;
		this.ctx = ctx;
	}

	public CaptureClass(ContextClass<T> ctx, String id) {
		this(ctx, id, ctx.position(), null);
	}

	public CaptureClass(CaptureClass<T> other) {
		if(other == null) throw new NullPointerException("other");
		this.id = other.id;
		this.ctx = other.ctx;
		this.start = other.start;
		this.end = other.end == null? null: other.end.intValue();
	}

	public ContextClass<T> ctx() {
		return this.ctx;
	}
	
	@Override
	public String id() {
		return this.id;
	}

	@Override
	public boolean matches(CharSequence id) {
		return (id == null? "": id).equals(this.id());
	}

	@Override
	public boolean matchesNoCase(CharSequence id) {
		return this.id.equalsIgnoreCase(id == null? "": id.toString());
	}

	@Override
	public int start() {
		return start;
	}

	@Override
	public CaptureClass<T> start(int value) {
		this.start = value;
		return this;
	}

	@Override
	public Integer end() {
		return end;
	}

	@Override
	public CaptureClass<T> end(Integer value) {
		this.end = value;
		return this;
	}

	@Override
	public String toString() {
		return Convert.toString(this);
	}

	public CaptureClass<T> getClone() {
		return new CaptureClass<>(this);
	}

	@Override
	public List<?> value() {
		return theValue();
	}
	
	public List<T> theValue(){
		return ctx.theSpan(start, end);
	}

}
