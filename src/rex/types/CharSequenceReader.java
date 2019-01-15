package rex.types;

public class CharSequenceReader extends BaseReader<Character>{

	private CharSequence source;
	
	public CharSequenceReader(CharSequence source) {
		if(source == null) throw new NullPointerException("source");
		this.source = source;
	}
	
	CharSequence source() {
		return source;
	}
	
	@Override
	public int count() {
		return source.length();
	}

	@Override
	protected Character getAt(int index) {
		if(index < 0 || index >= source.length()) return null;
		return source.charAt(index);
	}

}
