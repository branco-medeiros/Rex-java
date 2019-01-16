package rex.utils;

import java.util.Iterator;

import rex.Context;
import rex.interfaces.Range;
import rex.types.RangeClass;
import rex.types.ContextClass;

public class Contexts {

	// Lst<T>
	
	//ContextT<T>
	
	public static Context from(Context other){
		return other;
	}
	
	public static Context from(CharSequence other){
		return new ContextClass<>(Lists.from(other));
	}
	
	public static <T> Context from(Iterable<T> other){
		Context ctx = (Context) other;
		if(ctx != null) return ctx;
		
		return new ContextClass<T>(Lists.from(other));
	}
	
	public static <T> Context from(Iterator<T> other){
		Context ctx = (Context) other;
		if(ctx != null) return ctx;
		
		return new ContextClass<T>(Lists.from(other));
	}

	public static <T> Context from(T[] other){
		return new ContextClass<T>(Lists.from(other));
	}
	
	
	public static Range range(int start, Integer end) {
		return new RangeClass(start, end);
	}
	
	
}
