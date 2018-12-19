package rex.types;

import java.util.List;

public class SeqUtils {

	public static <T> Seq<T> Sequence(List<T> list){
		return new ListSeq<T>(list);
	}
	
	public static Seq<Character> Sequence(CharSequence list){
		return new CharSeq(list);
	}
}
