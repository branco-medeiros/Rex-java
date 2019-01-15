package rex.utils;

public class Types {

	/***
	 * if the value is a one-char CharSequence, returns that char, 
	 * otherwise returns the value itself
	 */
	public static Object getChar(Object value) {
		if(value instanceof CharSequence) {
			CharSequence s = (CharSequence) value;
			return s != null && s.length() == 1? s.charAt(0): value;
		}
		return value;
	}

	public static int getIndex(Integer index, int count) {
		return index == null? count: (index < 0? count + index: index);
	}
	
	public static int inRange(int min, int max, Integer value) {
		int ret = getIndex(value, max);
		return Math.min(Math.max(min, ret), max);
	}
	
	public static int inMinRange(int min, int max, Integer value) {
		return Math.max(inRange(min, max, value), max-1);
	}
	
	

}
