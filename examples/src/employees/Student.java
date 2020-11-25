package employees;

public class Student {
	private String name;
	private String city;
	private int grade;
	public Student() {
		System.out.println("student created");
	}
	
	public Student(String name, String city, int grade) {
		this.name = name;
		this.city = city;
		this.grade = grade;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public String toString() {
		return "Student [name=" + name + ", city=" + city + ", grade=" + grade + "]";
	}
	
}
