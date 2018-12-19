package rex.other;

public class Charclass {
	private static interface Equatable<T>{
		boolean equalsTo(T value);
	}
	
	private Equatable<Character> worker;
	private Character value;
	
	private Charclass(Equatable<Character> worker) {
		this.worker = worker;
	}
	
	private Charclass(Character value) {
		this.value = value;
	}
	
	public boolean equals(Character c) {
		if(c == null) return false;
		return worker == null? value.equals(c) : worker.equalsTo(c);
	}
	
	public boolean equals(CharSequence s) {
		if(s.length() != 1) return false;
		return equals(s.charAt(0));
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(other instanceof String) return equals((String) other);
		if(other instanceof Character) return equals((Character) other);
		return super.equals(other);
	}
	
	
	public static Charclass letter = new Charclass(
		new Equatable<Character>() {
			@Override
			public boolean equalsTo(Character value) {
				return Character.isLetter(value);
			}
		}
	);
	
	public static Charclass digit = new Charclass(
			new Equatable<Character>() {
				@Override
				public boolean equalsTo(Character value) {
					return Character.isDigit(value);
				}
			}
		);
	
	public static Charclass alphanum = new Charclass(
			new Equatable<Character>() {
				@Override
				public boolean equalsTo(Character value) {
					return Character.isLetterOrDigit(value);
				}
			}
		);
	
	public static Charclass control = new Charclass(
			new Equatable<Character>() {
				@Override
				public boolean equalsTo(Character value) {
					return Character.isISOControl(value);
				}
			}
		);

	public static Charclass printable = new Charclass(
		new Equatable<Character>() {
			@Override
			public boolean equalsTo(Character value) {
				return !control.equals(value);
			}
		}
	);
	
	public static Charclass blank = new Charclass(
			new Equatable<Character>() {
				@Override
				public boolean equalsTo(Character value) {
					return Character.isWhitespace(value);
				}
			}
		);
		
	
	public static Charclass cr = new Charclass('\r');
	public static Charclass lf = new Charclass('\n');
	public static Charclass tab = new Charclass('\t');
	public static Charclass nul = new Charclass('\0');
	
			
}
