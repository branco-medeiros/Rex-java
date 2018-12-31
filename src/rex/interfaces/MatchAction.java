package rex.interfaces;

import rex.types.MatchResult;
import rex.types.TContext;

public interface MatchAction<T> extends Action<TContext<T>, MatchResult<T>> {
	
}
