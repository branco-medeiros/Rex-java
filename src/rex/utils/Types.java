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
}
