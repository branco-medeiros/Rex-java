package rex.types;

public class LstChar extends LstBase<Character>{

	private CharSequence chars;
	
	public LstChar(CharSequence chars) {
		if(chars == null) throw new NullPointerException("chars");
		this.chars = chars;
	}
	
	@Override
	public Character get(int index) {
		return chars.charAt(getIndex(index));
	}

	@Override
	public Integer count() {
		return chars.length();
	}
	
	
	@Override
	public Character[] toArray() {
		Character[] ret = new Character[count()];
		for(int i=0, max=count(); i < max; ++i) {
			ret[i] = get(i);
		};
		return ret;
	}

}
