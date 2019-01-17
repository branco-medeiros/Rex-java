package rex.tests;

import rex.interfaces.Context;
import rex.interfaces.Result;
import rex.matchers.Rule;
import rex.testing.Test;
import rex.utils.Contexts;

public class TestContext {
	
	
	public static void run() {
		Test.run("CONTEXT", (t) -> {
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc\")", 
				() -> Contexts.from("abc")
			).isValid();
			
			t.ensure("ctx.moveNext()", ()-> ctx.moveNext()).isTrue();
			t.ensure("ctx.matches(\"b\")", () -> ctx.matches("b")).isTrue();
			t.ensure("ctx.matches('b')", () -> ctx.matches('b')).isTrue();
			t.ensure("ctx.matches(\"c\")", ()-> ctx.matches("c")).isFalse();
			t.ensure("ctx.finished()", ()-> ctx.finished()).isFalse();
			
			Result pr = (Result) t.ensure(
				"pr = ctx.enter(<identifier>)", 
				()-> ctx.enter(new Rule("identifier"))
			).isValid();
			
			t.ensure(
				"...and pr.toString()", 
				()-> pr.toString()
			).isEqual("[identifier: 1, ? vars:{} children:{}]");
			
			
			
		});
	}

}
