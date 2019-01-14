package rex.types;

import rex.interfaces.Range;

public class BaseRange implements Range{
	private int start;
	private Integer end;
	
	public BaseRange(int start, Integer end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int start() {
		return start;
	}

	@Override
	public Integer end() {
		return end;
	}
	
	BaseRange start(int value) {
		start = value;
		return this;
	}
	
	BaseRange end(Integer value) {
		end = value;
		return this;
	}
}
