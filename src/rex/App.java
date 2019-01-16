package rex;

import rex.tests.TestCharclass;
import rex.tests.TestContext;
import rex.tests.TestRex;

public class App {

	public static void main(String[] args) {
				
		System.out.println("Tests:");
		System.out.println("1. Charclass");
		System.out.println("2. Context");
		System.out.println("3. Rex.lit");
		System.out.println("4. Rex.opt");
		System.out.println("5. Rex.star");
		System.out.println("6. Rex.plus");
		System.out.println("7. Rex.and");
		System.out.println("8. Rex.or");
		
		// Scanner input = new Scanner(System.in);
		String opt = "8"; //input.nextLine();
		
		System.out.println(">> " + opt);
		switch(opt) {
		case "1": TestCharclass.run(); break;
		case "2": TestContext.run(); break;
		case "3": TestRex.testLit(); break;
		case "4": TestRex.testOpt(); break;
		case "5": TestRex.testStar(); break;
		case "6": TestRex.testPlus(); break;
		case "7": TestRex.testAnd(); break;
		case "8": TestRex.testOr(); break;
		default:
			System.out.println("Invalid option");
		}
	}
	
	
	
}
