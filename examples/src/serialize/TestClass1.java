/* 
 * Serializable classes must implement the Serializable interface 
 * but, this interface is only a marker-> it contains no methods
 * 
 * this helps to safeguard against serializing private information
 * 
 */

package serialize;

import java.io.Serializable;


public class TestClass1 implements Serializable {

	/* this is required for reasons beyond my current knowledge */
	private static final long serialVersionUID = 1L;
	
	private int age;
	private String name;
	private int income;
	
	public TestClass1() {
		age = 4;
		name = "Julio";
		income = 55555;
	}

	/* note that this has nothing to do with serialization, just printing */
	@Override
	public String toString() {
		return "TestClass1 [age=" + age + ", name=" + name + ", income=" + income + "]";
	}	
}
