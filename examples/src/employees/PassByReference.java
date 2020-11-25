package employees;

public class PassByReference {
	
	
	public static void main(String[] args) {
		int myVal = 4;
		myVal = doubleVal(myVal);
		System.out.println(myVal);
		System.out.println(doubleVal(myVal));
	}
	
	/* 
	 * this method works with the int argument
	 * 
	 * the compiler likes to know 
	 * since the argument is a primitive data type, the
	 *  compiler can predict how much memory needs to
	 *  be allocated each time the method is called  
	 */
	public static int doubleVal(int value) {
		value *= 2;
		return value;
	}
	
	/* when this method is called, it needs to work with */
	public static Employee changeEmployee(Employee E, String newName) {
		return E;
	}
}
