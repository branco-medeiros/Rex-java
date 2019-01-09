package rex.types;

import java.util.Arrays;
import java.util.List;

public class CharLst extends BaseLst<Character> implements CharSequence{

	private CharSequence chars;
	
	public CharLst(CharSequence chars) {
		if(chars == null) throw new NullPointerException("chars");
		this.chars = chars;
	}
	
	@Override
	public Character get(int index) {
		if(index < 0 || index >= count()) return null;
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

	@Override
	public List<Character> toList() {
		return Arrays.asList(toArray());
	}

	@Override
	public char charAt(int index) {
		return chars.charAt(index);
	}

	@Override
	public int length() {
		return chars.length();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return chars.subSequence(start,  end);
	}
}
