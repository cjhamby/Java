package exceptiontest;

public class MyClass implements MyInterface{
	
	/* throws a custom exception */
	public void foo() throws MyException {
		System.out.println("Foo Called");
		try {
			int i = 10/0;
		} catch( ArithmeticException e ) {
			
			/* log the exception for backend purposes and send the client useful info */
			System.out.println("Throwing my exception");
			throw(new MyException("something useful for client here", e));
		}
	}
}
