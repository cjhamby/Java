package exceptiontest;

public class ExceptionTest {

	/* demonstrate throwing and catching an error */
	public static void demo1() {
		System.out.println("DEMO1 METHOD\n");
		try {
			throw new NullPointerException("throwing error");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Caught the following error: " + e);
		}
		System.out.println("\n");
	}
	
	/* demonstrate cascaded catch blocks */
	public static void demo2( String v ) {
		System.out.println("DEMO2 METHOD\n");
		try {
			int a = Integer.parseInt(v);
			int b = a/10;
			int c = 10/0;
		} catch(ArithmeticException e) {
			System.out.println("Did you divide by 0?");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Index Out Of Bounds");
		} catch(Exception e) {
			System.out.println("Something else happened: " + e); 
		} finally {
			System.out.println("\n");
		}
	}
	
	/* demonstrates creating a custom exception for front-end readability */
	public static void demo3() {
		System.out.println("DEMO 3 Method");
		MyClass obj = new MyClass();
		try {
			obj.foo();				/* this will throw a custom error */
		} catch (MyException e) {
			System.out.println("Caught My Exception, message --- " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Caught Different Exception, message ---" + e.getMessage());
		}
	}
	
	
	
	public static void main(String[] args) {
		//demo1();
		//demo2("hey");
		demo3();
	}

}
