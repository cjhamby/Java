package employees;

public class StudentMain {

	public static void main(String[] args) {
		/* create a student */
		Student student = new Student();
		student.setName("admin");
		student.setGrade(25);
		student.setCity("Cary");
		
		/* create a more efficient student */
		Student student2 = new Student("Chris", "Cary", 24);
		
		/* use the toString() override in the Student class */
		System.out.println(student);
		System.out.println(student2);
	}
}
