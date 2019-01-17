package rex.utils;

import java.util.ArrayList;
import java.util.List;

import rex.interfaces.Capture;
import rex.interfaces.Predicate;
import rex.types.Stash;

public class Captures {

	/***
	 * gets a list of all occurrences of a capture with the specified name;
	 * more recent captures come first
	 */
	public static List<Capture> getAll(Stash<Capture> stk, String id){
		List<Capture> result = new ArrayList<Capture>();
		stk.each(new Predicate<Capture>() {
			@Override
			public Boolean eval(Capture value, int index) {
				if(value.matches(id)) result.add(value);
				return null;
			}
		});
		return result;
	}

	public static List<Capture> getAll(Stash<Capture> stk) {
		return getAll(stk, "");
	}
	
	/***
	 * returns the first (more recent) capture with the specified name
	 */
	public static Capture get(Stash<Capture> stk, String id){
		return stk.each(new Predicate<Capture>() {
			@Override
			public Boolean eval(Capture value, int index) {
				return value.matches(id);
			}
		});
	}
	
	/***
	 * returns the first (more recent) capture with id equal the empty string
	 */
	public static Capture get(Stash<Capture> stk) {
		return get(stk, "");
	}
	


}
