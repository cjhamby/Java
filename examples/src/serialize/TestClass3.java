package serialize;

import java.io.Serializable;

public class TestClass3 implements Serializable{
	
	private int age;
	private String name;
	private int income;
	
	/* transient keyword means it isn't serialized */
	private transient NestedClass child;
	
	/* nested classes can be serialized, if they implement Serializable */
	private NestedClass2 child2;
	
	public TestClass3() {
		age = 6;
		name = "Aaron";
		income = 55553;
		child = new NestedClass();
		child2 = new NestedClass2();
	}
	
	
	@Override
	public String toString() {
		String temp = "TestClass3 [age=" + age + ", name=" + name + ", income=" + income + "]";
		try {
			
			/* 
			 * when a TestClass3 object is created through serialization,
			 * "child" is never created
			 * trying to print child.nestInt will throw an exception
			 */
			temp = temp.concat(" Child Vals [" + child.nestInt + ", " + child2.nestInt + "]");
		} catch (Exception e) {
			temp = temp.concat(" Child Vals Not Found");
		} finally {
			return temp;
		}
	}



	private static class NestedClass {
		private int nestInt = 5;
	}
	
	private static class NestedClass2 implements Serializable {
		private int nestInt = 6;
	}
}
