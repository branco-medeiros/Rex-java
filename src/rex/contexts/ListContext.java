package rex.contexts;

import java.util.List;

import rex.Context;

public class ListContext<T> extends Context{

	private List<T> list;
	
	public ListContext(List<T> list) {
		super();
		if(list == null) throw new NullPointerException("list");
		this.list = list;
	}
	
	@Override
	public Object getAt(int position) {
		return this.getItem(position);
	}
	
	public T getItem(int position) {
		if(position < 0 || position >= this.list.size()) return null;
		return list.get(position);
	}
	
	@Override
	public boolean getFinished() {
		int p = this.getPosition();
		return p < 0 || p >= this.list.size();
	}

	@Override
	public Context clone() {
		ListContext<T> result = new ListContext<T>(this.list);
		return result.assign(this);
	}

}
