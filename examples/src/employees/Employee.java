package employees;


public class Employee implements Comparable<Employee>{
	private int empId;
	private String empName;
	private int income;
	private String department;
	
	public Employee(int empId, String empName, int income, String department) {
		this.empId = empId;
		this.empName = empName;
		this.income = income;
		this.department = department;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Employee " + this.empName + "\tID Number " + this.empId);
	}
	
	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return (o.getEmpId() - this.getEmpId());
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj)	{
			return true;
		}
		
		return (compareTo((Employee)obj) == 0);
	}
}
