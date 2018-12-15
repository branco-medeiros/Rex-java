package rex.types;

public class CharClass{

	private static interface Equatable<T>{
		boolean equalsTo(T value);
	}
	
	private Equatable<Character> worker;
	
	private CharClass(Equatable<Character> worker) {
		this.worker = worker;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(other instanceof String) {
			String s = (String) other;
			return s.length() == 1?  this.worker.equalsTo(s.charAt(0)): false;
		}
		
		if(other instanceof Character) {
			return this.worker.equalsTo((Character) other);
		}
		
		return super.equals(other);
	}
	
	
	public static CharClass letter = new CharClass(
		new Equatable<Character>() {
			@Override
			public boolean equalsTo(Character value) {
				return Character.isLetter(value);
			}
		}
	);
	
	public static CharClass digit = new CharClass(
			new Equatable<Character>() {
				@Override
				public boolean equalsTo(Character value) {
					return Character.isDigit(value);
				}
			}
		);
	
	public static CharClass alphanum = new CharClass(
			new Equatable<Character>() {
				@Override
				public boolean equalsTo(Character value) {
					return Character.isLetterOrDigit(value);
				}
			}
		);
	
	public static CharClass control = new CharClass(
			new Equatable<Character>() {
				@Override
				public boolean equalsTo(Character value) {
					return Character.isISOControl(value);
				}
			}
		);
	
}
