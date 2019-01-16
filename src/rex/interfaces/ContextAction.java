package rex.interfaces;

import rex.Context;

public interface ContextAction extends Action<Context, Boolean> {
	Boolean eval(Context ctx);
}
