package rex.writers;

import rex.interfaces.Writer;

public class CharSequenceWriter implements Writer<CharSequence, Character>{

	private StringBuilder sb = new StringBuilder();
	
	@Override
	public CharSequenceWriter write(Iterable<Character> values) {
		for(Character c: values)  sb.append(c);
		return this;
	}
	
	@Override
	public Writer<CharSequence, Character> append(Character value) {
		sb.append(value);
		return this;
	}

	@Override
	public CharSequence getValue() {
		return sb.toString();
	}

}
