package rex.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rex.ReplaceAction;
import rex.interfaces.Capture;
import rex.interfaces.Context;
import rex.interfaces.Result;

public class Convert {

	/***
	 * if the value is a one-char CharSequence, returns that char, 
	 * otherwise returns the value itself
	 */
	public static Object toChar(Object value) {
		if(value instanceof CharSequence) {
			CharSequence s = (CharSequence) value;
			return s != null && s.length() == 1? s.charAt(0): value;
		}
		return value;
	}

	public static int getIndex(int index, int count) {
		return index < 0? count + index: index;
	}
	
	public static int getIndex(Integer index, int count) {
		return index == null? count: getIndex(index.intValue(), count);
	}
	
	public static int inRange(int min, int max, int value) {
		int ret = getIndex(value, max);
		return Math.min(Math.max(min, ret), max);
	}
	
	public static int inRange(int min, int max, Integer value) {
		int ret = getIndex(value, max);
		return Math.min(Math.max(min, ret), max);
	}
	
	/***
	 * coerces value to the range(min, max-1);
	 * if value is negative it is first coverted with getIndex()
	 */
	public static int inMinRange(int min, int max, int value) {
		return Math.max(inRange(min, max, value), max-1);
	}
	
	public static String toString(Result pr) {
		if(pr == null) return "[<NULL>]";
		StringBuilder s = new StringBuilder();
		s.append("[");
		s.append(pr.id());
		s.append(": ");
		s.append(pr.start());
		s.append(", ");
		Integer end = pr.end();
		s.append(end == null? "?": end.toString());
		s.append(" vars:{");
		Set<String> names = new HashSet<String>();
		for(Capture c: pr.vars().toList()) {
			names.add(c.id());
		}
		s.append(String.join(", ", names));

		s.append("} children:{");
		names = new HashSet<String>();
		for(Result p: pr.children().toList()) {
			names.add(toString((Capture) p));
		}
		s.append(String.join(", ", names));
		s.append("}]");
		return s.toString();
	}
	
	public static String toString(Capture c) {
		if(c == null) return "[<NULL>]";
		Integer end = c.end();
		return String.format("[%s: %d, %s]", c.id(), c.start(), (end == null? "?": end.toString()));
	}

	
	public static <T> ReplaceAction<T> toReplace(Iterable<T> source){
		return new ReplaceAction<T>() {
			@Override
			public Iterable<T> eval(Context arg) {
				return source;
			}
		};
	}
	
	public static <T> ReplaceAction<T> toReplaceFromCapture(String id){
		return new ReplaceAction<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public Iterable<T> eval(Context ctx) {
				Capture cap = ctx.result().var(id);
				if(cap == null) return Collections.<T>emptyList();
				List<T> ret = (List<T>) cap.value();
				return ret;
			}
		};
	}
	
	public static ReplaceAction<Character> toReplace(CharSequence source){
		return toReplace(Lists.from(source));
	}
	
	
	
	
}
