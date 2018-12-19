package rex.other;

public abstract class SpnBase<T> extends LstBase<T> implements Spn<T> {

	protected Lst<T> src;
	protected int start;
	protected Integer end;
	
	public SpnBase(Lst<T> src, int start, Integer end) {
		this.src = src;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public Lst<T> source() {
		return src;
	}

	@Override
	public int start() {
		return start;
	}

	@Override
	public Integer end() {
		int cnt = src.count();
		return src == null? null: Math.min((end == null? cnt: end), cnt);
	}

	@Override
	public T get(int index) {
		if(src == null) return null;
		index = getIndex(index);
		return (index < 0 || index > count())? null: src.get(start+index);
	}

	@Override
	public Integer count() {
		int end = end();
		return src == null? 0: Math.max(0,  end - start);
	}

	@Override
	public T each(Predicate<T> fn) {
		T ret = null;
		int index = 0;
		for(int i = start, max = end(); i < max; ++i) {
			ret = src.get(i);
			if(fn.eval(ret, index)) break;
			index += 1;
		}
		return ret;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] ret = (T[]) new Object[count()];
		int j = 0;
		for(int i=start, max = end(); i < max; ++i) {
			ret[j] = src.get(i);
			j += 1;
		}
		return ret;
	}


}
