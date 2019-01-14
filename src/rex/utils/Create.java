package rex.utils;

import java.util.Iterator;
import java.util.List;

import rex.Context;
import rex.interfaces.Lst;
import rex.interfaces.Range;
import rex.interfaces.Seq;
import rex.interfaces.Spn;
import rex.interfaces.Stk;
import rex.types.ArrayLst;
import rex.types.BaseRange;
import rex.types.CharLst;
import rex.types.IteratorLst;
import rex.types.TContext;
import rex.types.TLst;
import rex.types.TSeq;
import rex.types.TSpn;
import rex.types.TStk;

public class Create {

	// Lst<T>
	
	public static <T> Lst<T> lstFrom(Lst<T> list){
		return list;
	}

	public static <T> Lst<T> lstFrom(List<T> list){
		return new TLst<T>(list);
	}

	public static <T> Lst<T> lstFrom(T[] list){
		return new ArrayLst<T>(list);
	}
	
	public static Lst<Character> lstFrom(CharSequence chars){
		return new CharLst(chars);
	}
	
	public static <T> Lst<T> lstFrom(Iterable<T> iter){
		Lst<T> lst = (Lst<T>) iter;
		if(lst != null) return lst;
		
		List<T> list = (List<T>) iter;
		if(list != null) return lstFrom(list);
		
		return new IteratorLst<>(iter);
	}
	
	public static <T> Lst<T> lstFrom(Iterator<T> iter){
		return new IteratorLst<T>(iter);
	}
	
	//Spn<T>
	
	public static <T> Spn<T> spnFrom(Lst<T> other, int start, Integer end){
		return new TSpn<T>(other, start, end);
	}
	
	public static <T> Spn<T> spnFrom(Iterable<T> other, int start, Integer end){
		return new TSpn<T>(lstFrom(other), start, end);
	}
	
	public static <T> Spn<T> spnFrom(Iterator<T> other, int start, Integer end){
		return new TSpn<T>(lstFrom(other), start, end);
	}
	
	public static <T> Spn<T> spnFrom(T[] list, int start, Integer end){
		return new TSpn<T>(lstFrom(list), start, end);
	}
	
	public static Spn<Character> spnFrom(CharSequence list, int start, Integer end){
		return new TSpn<Character>(lstFrom(list), start, end);
	}
	
	//Seq<T>
	
	public static <T> Seq<T> seqFrom(Lst<T> other){
		return new TSeq<T>(other);
	}

	public static <T> Seq<T> seqFrom(T[] other){
		return new TSeq<T>(lstFrom(other));
	}

	public static <T> Seq<T> seqFrom(Iterable<T> other){
		return new TSeq<T>(lstFrom(other));
	}

	public static <T> Seq<T> seqFrom(Iterator<T> other){
		return new TSeq<T>(lstFrom(other));
	}
	
	public static Seq<Character> seqFrom(CharSequence other){
		return new TSeq<Character>(lstFrom(other));
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
	
	public static Context contextFrom(Context other){
		return other;
	}
	
	public static Context contextFrom(CharSequence other){
		return new TContext<Character>(lstFrom(other));
	}
	
	public static <T> Context contextFrom(Iterable<T> other){
		TContext<T> ctx = (TContext<T>) other;
		if(ctx != null) return ctx;
		
		return new TContext<T>(lstFrom(other));
	}
	
	public static <T> Context contextFrom(Iterator<T> other){
		TContext<T> ctx = (TContext<T>) other;
		if(ctx != null) return ctx;
		
		return new TContext<T>(lstFrom(other));
	}

	public static <T> Context contextFrom(T[] other){
		return new TContext<T>(lstFrom(other));
	}
	
	
	public static Range range(int start, Integer end) {
		return new BaseRange(start, end);
	}
	
	
}
