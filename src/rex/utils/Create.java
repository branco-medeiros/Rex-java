package rex.utils;

import java.util.Iterator;
import java.util.List;

import rex.interfaces.Lst;
import rex.interfaces.Seq;
import rex.interfaces.Spn;
import rex.interfaces.Stk;
import rex.types.ArrayLst;
import rex.types.CharContext;
import rex.types.CharLst;
import rex.types.IteratorLst;
import rex.types.TContext;
import rex.types.TLst;
import rex.types.TSeq;
import rex.types.TSpn;
import rex.types.TStk;

public class Create {

	// Lst<T>
	
	public static <T> Lst<T> lstFrom(List<T> list){
		return new TLst<T>(list);
	}

	public static <T> Lst<T> lstFrom(T[] list){
		return new ArrayLst<T>(list);
	}
	
	public static Lst<Character> lstFrom(CharSequence chars){
		return new CharLst(chars);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Lst<T> lstFrom(Iterable<T> iter){
		T ref = null;
		if(ref instanceof Character) {
			CharSequence chars = (CharSequence) iter;
			if(chars != null) return (Lst<T>) chars;
		}
		
		List<T> list = (List<T>) iter;
		if(list != null) return lstFrom(list);
		
		Lst<T> lst = (Lst<T>) iter;
		if(lst != null) return lst;
		
		return new IteratorLst<>(iter);
	}
	
	public static <T> Lst<T> lstFrom(Iterator<T> iter){
		return new IteratorLst<T>(iter);
	}
	
	//Spn<T>
	
	public static <T> Spn<T> spnFrom(Lst<T> list, int start, Integer end){
		return new TSpn<T>(list, start, end);
	}
	
	public static <T> Spn<T> spnFrom(List<T> list, int start, Integer end){
		return new TSpn<T>(lstFrom(list), start, end);
	}
	
	public static <T> Spn<T> spnFrom(T[] list, int start, Integer end){
		return new TSpn<T>(lstFrom(list), start, end);
	}
	
	public static Spn<Character> spnFrom(CharSequence chars, int start, Integer end){
		return new TSpn<Character>(lstFrom(chars), start, end);
	}
	
	//Seq<T>
	
	public static <T> Seq<T> seqFrom(Lst<T> list){
		return new TSeq<T>(list);
	}
	
	public static <T> Seq<T> seqFrom(List<T> list){
		return new TSeq<T>(lstFrom(list));
	}
	
	public static <T> Seq<T> seqFrom(T[] list){
		return new TSeq<T>(lstFrom(list));
	}
	
	public static Seq<Character> seqFrom(CharSequence chars){
		return new TSeq<Character>(lstFrom(chars));
	}
	
	
	//Stk<T>
	
	public static <T> Stk<T> stk(){
		return new TStk<T>();
	}


	public static <T> Stk<T> stkFrom(T value){
		return new TStk<T>(value);
	}
	
	public static <T> Stk<T> stkFrom(Stk<T> value){
		return new TStk<T>(value);
	}
	
	//ContextT<T>
	
	public static <T> TContext<T> contextFrom(List<T> list){
		return new TContext<T>(lstFrom(list));
	}
	
	public static TContext<Character> contextFrom(CharSequence list){
		return new CharContext(lstFrom(list));
	}
	
	public static <T> TContext<T> contextFrom(Lst<T> list){
		return new TContext<T>(list);
	}
	
	
}
