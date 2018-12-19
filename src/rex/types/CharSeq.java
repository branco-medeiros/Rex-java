package rex.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharSeq implements Seq<Character>{

	private CharSequence list;
	
	public CharSeq(CharSequence list) {
		if(list == null) throw new NullPointerException("list");
		this.list = list;
	}
	
	@Override
	public Character get(int index) {
		return list.charAt(index);
	}

	@Override
	public int count() {
		return list.length();
	}

	@Override
	public List<Character> toList() {
		List<Character> chars = new ArrayList<Character>();
		Iterator<Character> it = iterator();
		while(it.hasNext()) chars.add(it.next());
		return chars;
	}

	@Override
	public Character[] toArray() {
		return (Character[]) toList().toArray();
	}

	@Override
	public Iterator<Character> iterator() {
		return null;
	}

}
