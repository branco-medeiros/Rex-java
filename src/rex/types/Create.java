package rex.types;

import java.util.List;

public class Create {

	// Lst<T>
	
	public static <T> Lst<T> lstFrom(List<T> list){
		return new LstT<T>(list);
	}

	public static <T> Lst<T> lstFrom(T[] list){
		return new LstArray<T>(list);
	}
	
	public static Lst<Character> lstFrom(CharSequence chars){
		return new LstChar(chars);
	}
	
	//Spn<T>
	
	public static <T> Spn<T> spnFrom(Lst<T> list, int start, Integer end){
		return new SpnT<T>(list, start, end);
	}
	
	public static <T> Spn<T> spnFrom(List<T> list, int start, Integer end){
		return new SpnT<T>(lstFrom(list), start, end);
	}
	
	public static <T> Spn<T> spnFrom(T[] list, int start, Integer end){
		return new SpnT<T>(lstFrom(list), start, end);
	}
	
	public static Spn<Character> spnFrom(CharSequence chars, int start, Integer end){
		return new SpnT<Character>(lstFrom(chars), start, end);
	}
	
	//Seq<T>
	
	public static <T> Seq<T> seqFrom(Lst<T> list){
		return new SeqT<T>(list);
	}
	
	public static <T> Seq<T> seqFrom(List<T> list){
		return new SeqT<T>(lstFrom(list));
	}
	
	public static <T> Seq<T> seqFrom(T[] list){
		return new SeqT<T>(lstFrom(list));
	}
	
	public static Seq<Character> seqFrom(CharSequence chars){
		return new SeqT<Character>(lstFrom(chars));
	}
	
	
	//Stk<T>
	
	public static <T> Stk<T> stk(){
		return new StkT<T>();
	}


	public static <T> Stk<T> stkFrom(T value){
		return new StkT<T>(value);
	}
	
	public static <T> Stk<T> stkFrom(Stk<T> value){
		return new StkT<T>(value);
	}
	
	//ContextT<T>
	
	public static <T> ContextT<T> contextFrom(List<T> list){
		return new ContextT<T>(lstFrom(list));
	}
	
	public static ContextT<Character> contextFrom(CharSequence list){
		return new ContextChar(lstFrom(list));
	}
	
	public static <T> ContextT<T> contextFrom(Lst<T> list){
		return new ContextT<T>(list);
	}
	
	
}
