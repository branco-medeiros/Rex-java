package rex.types;

import rex.interfaces.Range;

public class RangeClass implements Range{
	private int start;
	private Integer end;
	
	public RangeClass(int start, Integer end) {
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
	
	public RangeClass start(int value) {
		start = value;
		return this;
	}
	
	public RangeClass end(Integer value) {
		end = value;
		return this;
	}
}
