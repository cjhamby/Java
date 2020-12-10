package employees;

public class Student implements Comparable<Student>{
	private int id;
	private String name;
	private String city;
	private String subject;
	
	public Student(int id, String name, String city, String subject) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public int compareTo(Student o) {
		return (this.getId() - o.getId());
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + ", is studying " + subject + "]";
	}
}
