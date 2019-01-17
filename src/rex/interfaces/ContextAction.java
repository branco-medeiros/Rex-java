package rex.interfaces;

public interface ContextAction extends Action<Context, Boolean> {
	Boolean eval(Context ctx);
}
