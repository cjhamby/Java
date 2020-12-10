package worksheet;

public class UserType {

	String name;
	
	UserType() {
		this("Student");
	}
	
	UserType(String parameterVal) {
		name= parameterVal;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserType type1 = new UserType("Faculty");
		UserType type2 = new UserType();
		
		System.out.println(type1.name);
		System.out.println(type2.name);
		
	}

}
