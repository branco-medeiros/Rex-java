package rex.tests;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	//Test.run("test description", (t) -> {
	//  t.ensure("what", (result.class ) -> action).isTrue()
	//                                           .isFalse()
	//                                           .equals(v)
	//                                           .notEquals(v)
	//                                           .is(v)
	//																					 .isNot(v)
	//																					 .mustThrow()
	//                                           .mustNotThrow()
	//                                           .isValid()
	//																					 .isNotValid()
	//                             
	//})
	
	public static class TestInfo{
		public int index;
		public Object result;
		public Exception error;
		public TestSubject subject;
		public boolean status;
		public String desc;
		public Test test;
		
		private Object report(boolean ok, String msg, String what ) {
			
			this.test.print(String.format("%02d. [%s] - %s %s",
				this.index,
				(ok? "OK": "XX"),
				msg,
				what
			));

			this.status = ok;
			return this.result;
		}
		
		public Object didThrow() {
			return this.report(error != null, desc, "MUST THROW"); 
		}

		public Object didNotThrow() {
			return this.report(error == null, desc, "MUST NOT THROW"); 
		}
		
		public Object isValid() {
			return this.report(error == null && result != null, desc, "IS VALID"); 
		}
		
		public Object isNotValid() {
			return this.report(error != null || result == null, desc, "IS NOT VALID"); 
		}
		
		public Object isTrue() {
			boolean ok = result instanceof Boolean && ((Boolean) result) == true;
			return this.report(error == null && ok, desc, "IS TRUE"); 
		}
		
		public Object isFalse() {
			boolean ok = result instanceof Boolean && ((Boolean) result) != true;
			return this.report(error == null && ok, desc, "IS FALSE"); 
		}
		
	}
	
	private List<TestInfo> tests = new ArrayList<TestInfo>();
	
	protected void print(String msg) {
		System.out.println(msg == null? "": msg);
	}
	
	public int getSuccess() {
		return this.tests.stream().mapToInt((v)-> v.status? 1:0).sum();
	}
	
	public int getCount() {
		return this.tests.size();
	}
	
	public TestInfo ensure(String description, TestSubject subj) {
		TestInfo ti = new TestInfo();
		this.tests.add(ti);
		ti.index = this.tests.size();
		ti.desc = description;
		ti.subject = subj;
		ti.test = this;
		
		try {
			ti.result = subj.eval();
			
		} catch(Exception ex) {
			ti.error = ex;
		}
		
		return ti;
	}
	
	public static void run(String desc, Testable t) {
		System.out.println(desc == null? "TEST (no title)": "TEST  " + desc);
		try {
			Test test = new Test();
			t.run(test);
			int success = test.getSuccess();
			int count = test.getCount();
			
			System.out.println(String.format(">> TESTS: %d -- SUCCEEDED: %d", count, success));
			boolean ok = count > 0 && success == count;
			System.out.println(String.format(">> TEST %s", (ok? "SUCCEEDED": "FAILED")));
			
			
		}catch(Exception ex) {
			System.out.println("");
			System.out.println(">> TEST WAS INTERRUPTED BY EXCEPTION");
			ex.printStackTrace();
		}
		System.out.println("");
	}


}
