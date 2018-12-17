package rex;

import rex.tests.TestCharclass;
import rex.types.Charclass;

public class App {

	public static void main(String[] args) {
		TestCharclass.run();
		/*
		Context ctx = Context.from("a1B2\r\t");
		System.out.println("[0] letter: " + (ctx.matchAt(0, Charclass.letter)? "y": "n"));
		System.out.println("[1] digit: " + (ctx.matchAt(1, Charclass.digit)? "y": "n"));
		System.out.println("[2] alphanum: " + (ctx.matchAt(2, Charclass.alphanum)? "y": "n"));
		System.out.println("[3] printable: " + (ctx.matchAt(3, Charclass.printable)? "y": "n"));
		System.out.println("[4] control: " + (ctx.matchAt(4, Charclass.control)? "y": "n"));
		System.out.println("[5] tab: " + (ctx.matchAt(5, Charclass.tab)? "y": "n"));
		*/
	}
}
