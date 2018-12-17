package rex.tests;

import rex.types.Charclass;

public class TestCharclass {
	public static void run() {
		Test.run("CHARCLASS", (Test t) -> {
			t.ensure("Charclass.letter.equals(\"A\")", ()-> Charclass.letter.equals("A")).isTrue();
			t.ensure("Charclass.letter.equals('A')", ()-> Charclass.letter.equals('A')).isTrue();
			t.ensure("Charclass.letter.equals(\"Ã\")", ()-> Charclass.letter.equals("Ã")).isTrue();
			t.ensure("Charclass.letter.equals('9')", ()-> Charclass.letter.equals('9')).isFalse();
			t.ensure("Charclass.letter.equals('-')", ()-> Charclass.letter.equals('-')).isFalse();
			t.ensure("Charclass.digit.equals('0')", ()-> Charclass.digit.equals('0')).isTrue();
			t.ensure("Charclass.digit.equals('9')", ()-> Charclass.digit.equals('9')).isTrue();
			t.ensure("Charclass.digit.equals('A')", ()-> Charclass.digit.equals('A')).isFalse();
			t.ensure("Charclass.digit.equals('-')", ()-> Charclass.digit.equals('-')).isFalse();
			t.ensure("Charclass.control.equals('A')", ()-> Charclass.control.equals('A')).isFalse();
			t.ensure("Charclass.control.equals('9')", ()-> Charclass.control.equals('9')).isFalse();
			t.ensure("Charclass.control.equals('-')", ()-> Charclass.control.equals('-')).isFalse();
			t.ensure("Charclass.control.equals('\\t')", ()-> Charclass.control.equals('\t')).isTrue();
			t.ensure("Charclass.control.equals('\\n')", ()-> Charclass.control.equals('\n')).isTrue();
		});
		
		
	}
}
