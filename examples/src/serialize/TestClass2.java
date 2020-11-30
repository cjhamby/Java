/* the same as TestClass1, but without the Serializable interface */

package serialize;


public class TestClass2 {


	private int age;
	private String name;
	private int income;
	
	public TestClass2() {
		age = 5;
		name = "Bea";
		income = 55554;
	}

	/* note that this has nothing to do with serialization, just printing */
	@Override
	public String toString() {
		return "TestClass1 [age=" + age + ", name=" + name + ", income=" + income + "]";
	}	
}