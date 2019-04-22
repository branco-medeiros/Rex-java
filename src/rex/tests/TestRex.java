package rex.tests;

import rex.Charclass;
import rex.Matcher;
import rex.Rex;
import rex.interfaces.Context;
import rex.testing.Test;
import rex.utils.Contexts;

public class TestRex {
	
	public static void 	testLit(){
		Test.run("REX LIT", (Test t)->{
			
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"ab\")", 
				()-> Contexts.from("ab")
			).isValid();
			
			Matcher lit = (Matcher) t.ensure(
				"lit = Rex.lit(\"a\")",
				() -> Rex.lit("a")
			).isValid();
			
			t.ensure(
				"lit.match(ctx)",
				()-> lit.match(ctx)
			).isTrue();
						
			t.ensure(
				"...and ctx.position == 1",
				() -> ctx.position() == 1
			).isTrue();
			
			t.ensure(
				"lit.match(ctx)",
				()-> lit.match(ctx)
			).isFalse();
			
			t.ensure(
				"...and ctx.position == 1",
				()-> ctx.position() == 1
			).isTrue();
		});
	}
	
	public static void testOpt() {
		Test.run("REX OPT", (t)->{

			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc\")", 
				() -> Contexts.from("abc")
			).isValid();
				
			Matcher opt = (Matcher) t.ensure(
				"opt = Rex.opt(\"a\")", 
				()-> Rex.opt("a")
			).isValid();
			
			t.ensure(
				"opt.match(ctx)",
				()-> opt.match(ctx)
			).isTrue();
	
			t.ensure(
				"...and ctx.position == 1",
				()-> ctx.position() == 1
			).isTrue();
	
	
			t.ensure(
				"opt.match(ctx)",
				()-> opt.match(ctx)
			).isTrue();
	
			t.ensure(
				"...and ctx.position == 1",
				()-> ctx.position() == 1
			).isTrue();
		
		});
	}
	

	public static void 	testStar(){
		Test.run("REX STAR", (t) ->{
		
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc123\")",
				()-> Contexts.from("abc123")
			).isValid();
			
			Matcher star = (Matcher) t.ensure(
				"star = Rex.star(<letter>)",
				()-> Rex.star(Charclass.letter)
			).isValid();
	
			t.ensure(
				"star.match(ctx)",
				()-> star.match(ctx)
			).isTrue();
	
			t.ensure(
				"...and ctx.position", 
				()->ctx.position()
			).isEqual(3);
			
			t.ensure(
				"star.match(ctx)", 
				()-> star.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position", 
				()->ctx.position()
			).isEqual(3);
				
		});

	} //testStar
	
	
	public static void testPlus() {
		
		Test.run("REX PLUS", (t) ->{;
		
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc123\")",
				()-> Contexts.from("abc123")
			).isValid();
			
			Matcher plus = (Matcher) t.ensure(
				"plus = Rex.plus(<letter>)",
				() -> Rex.plus(Charclass.letter)
			).isValid();
	
			t.ensure(
				"plus.match(ctx) //[a]bc123",
				()-> plus.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
			
			t.ensure(
					"plus.match(ctx) //abc[1]23",
					()-> plus.match(ctx)
				).isFalse();
				
				t.ensure(
					"...and ctx.position",
					()-> ctx.position()
				).isEqual(3);
		});
	} //testPlus
	
	public static void testAnd() {
		Test.run("REX AND", (t)->{
		
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc123\")",
				()-> Contexts.from("abc123")
			).isValid();
				
			Matcher m = (Matcher) t.ensure(
				"m = Rex.and(\"a\", \"b\", \"c\")",
				() -> Rex.and("a", "b", "c")
			).isValid();
		
			t.ensure(
				"m.match(ctx) //[a]bc123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
			
			t.ensure(
				"m.match(ctx) //abc[1]23",
				()-> m.match(ctx)
			).isFalse();
				
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
		});

	} //testAnd
	
	public static void testOr() {
		Test.run("REX OR", (t)->{
		
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc123\")",
				()-> Contexts.from("abc123")
			).isValid();
				
			Matcher m = (Matcher) t.ensure(
				"m = Rex.or(\"a\", \"b\", \"c\")",
				() -> Rex.or("a", "b", "c")
			).isValid();
		
			t.ensure(
				"m.match(ctx) //[a]bc123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(1);
			
			t.ensure(
				"m.match(ctx) //a[b]c123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(2);
				
			t.ensure(
				"m.match(ctx) //ab[c]123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
				
			
			t.ensure(
				"m.match(ctx) //abc[1]23",
				()-> m.match(ctx)
			).isFalse();
				
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
		});

	} //testOr
	

	public static void testSeq() {
		Test.run("REX SEQ", (t)->{
		
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc123\")",
				()-> Contexts.from("abc123")
			).isValid();
				
			Matcher m = (Matcher) t.ensure(
				"m = Rex.seq(\"abc\")",
				() -> Rex.seq("abc")
			).isValid();
		
			t.ensure(
				"m.match(ctx) //[a]bc123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
			
			t.ensure(
				"m.match(ctx) //abc[1]23",
				()-> m.match(ctx)
			).isFalse();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
		});

	} //testSeq
	

	public static void testOneOf() {
		Test.run("REX ONE-OF", (t)->{
		
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc123\")",
				()-> Contexts.from("abc123")
			).isValid();
				
			Matcher m = (Matcher) t.ensure(
				"m = Rex.oneof(\"abc\")",
				() -> Rex.oneof("abc")
			).isValid();
		
			t.ensure(
				"m.match(ctx) //[a]bc123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(1);
			
			t.ensure(
				"m.match(ctx) //a[b]c123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(2);
				
			t.ensure(
				"m.match(ctx) //ab[c]123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
				
			
			t.ensure(
				"m.match(ctx) //abc[1]23",
				()-> m.match(ctx)
			).isFalse();
				
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
		});

	} //testOr
	
	public static void testIs() {
		Test.run("REX IS", (t)->{
			
			Context ctx = (Context) t.ensure(
				"ctx = Create.contextFrom(\"abc123\")",
				()-> Contexts.from("abc123")
			).isValid();
				
			Matcher m = (Matcher) t.ensure(
				"m = Rex.is(\"a\" \"b\" \"c\")",
				() -> Rex.is("'a' 'b' 'c'")
			).isValid();
		
			t.ensure(
				"m.match(ctx) //[a]bc123",
				()-> m.match(ctx)
			).isTrue();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(0);
			
			ctx.position(3);
			
			t.ensure(
				"m.match(ctx) //abc[1]23",
				()-> m.match(ctx)
			).isFalse();
			
			t.ensure(
				"...and ctx.position",
				()-> ctx.position()
			).isEqual(3);
				
		});
	} //testIs
	

	public static void testAll() {
		testLit();
		testAnd();
		testOr();
		testOpt();
		testPlus();
		testStar();
		testSeq();
		testOneOf();
		testIs();
	}
}
