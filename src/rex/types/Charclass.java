package rex.types;

public class Charclass{

	private static interface Equatable<T>{
		boolean equalsTo(T value);
	}
 
	private Equatable<Character> worker;
	private Character value;
	
	private Charclass(Equatable<Character> worker) {
		this.worker = worker;
	}
 
	private Charclass(Character value) {
		if(value == null) throw new NullPointerException("value");
		this.value = value;
	}
 
	public boolean equals(char ch) {
		return this.worker == null? this.value.equals(ch)
				: this.worker.equalsTo(ch);
	}

	public boolean equals(Character ch) {
		if(ch == null) return false;
		return this.worker == null? this.value.equals(ch)
				: this.worker.equalsTo(ch);
	}
	
	public boolean equals(String ch) {
		if(ch == null || ch.length() != 1) return false;
		return this.equals(ch.charAt(0));
	}
	 

	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(other instanceof Character) return this.equals((Character) other);
		if(other instanceof String) return this.equals((String) other);
		
		return super.equals(other);
	}
 
	
 	public static Charclass letter = 
 	new Charclass(new Equatable<Character>() {
 		@Override
 		public boolean equalsTo(Character value) {
 			return Character.isLetter(value);
 		}
 	});
 
 	public static Charclass digit = 
 	new Charclass(new Equatable<Character>() {
    @Override
    public boolean equalsTo(Character value) {
     return Character.isDigit(value);
    }
  });
 
 	public static Charclass alphanum = 
 	new Charclass(new Equatable<Character>() {
  	@Override
  	public boolean equalsTo(Character value) {
  		return Character.isLetterOrDigit(value);
  	}
  });
 
 	public static Charclass control = 
 	new Charclass(new Equatable<Character>() {
    @Override
    public boolean equalsTo(Character value) {
    	return Character.isISOControl(value);
    }
  });
 
 	public static Charclass printable = 
 	new Charclass(new Equatable<Character>() {
	  public boolean equalsTo(Character value) {
	  	return !Character.isISOControl(value);
	  }
  });

 	public static Charclass blank = 
 	new Charclass(new Equatable<Character>() {
 		public boolean equalsTo(Character value) {
 			return Character.isSpaceChar(value);
 		}
	});
 
 	public static Charclass cr =  new Charclass('\r');
 	
 	public static Charclass lf = new Charclass('\n');
 	
 	public static Charclass tab = new Charclass('\t');
 	
 	public static Charclass nul = new Charclass('\0');
 	
 	
}
