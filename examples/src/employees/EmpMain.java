package employees;

/* different collections have to be imported */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class EmpMain {
	public static void main(String[] args) {
		
		/* lets first demonstrate comparing only two employees */
		/* arguments: ID, NAME, INCOME, DEPARTMENT */
		Employee employee1 = new Employee(1234, "Charlee", 5, "Things");
		Employee employee2 = new Employee(1234, "Will",   10, "Stuff");
		
		/* toString() override determines what is printed */
		System.out.println(employee1);
		
		/* equals() override compares empId values for a boolean */
		System.out.println("Equals\t\t" + employee1.equals(employee2));
		
		/* we can us the class compareTo() method to compare objects */
		System.out.println("compareTo\t" + employee1.compareTo(employee2));
		
		/* we can use a comparator's compare() method directly */
		System.out.println("Compare (ID)\t" 
                + new EmpIdComparator().compare(employee1, employee2));
		
		/* using a different comparator */
		System.out.println("Compare (Name)\t"
                + new EmpNameComparator().compare(employee1, employee2));
		
		/*
		 *  we can also use a comparator to sort Collections
		 * try it with different types of Collections! 
		 * uncomment the desired collection type
		 */
		
		/* sort works well with these */
		//LinkedList<Employee> myEmps = new LinkedList<Employee>();
		ArrayList<Employee> myEmps = new ArrayList<Employee>();
		
		
		/* TreeSet doesn't have a sort method; is auto-sorted */
		//TreeSet<Employee> myEmps = new TreeSet<Employee>();
		//HashSet<Employee> myEmps = new HashSet<Employee>(); 
		
		
		myEmps.add(new Employee(111, "Adam"  , 42, "Things"));
		myEmps.add(new Employee(116, "Bob"   , 18, "Things"));
		myEmps.add(new Employee(115, "Carly" ,  7, "Things"));
		myEmps.add(new Employee(112, "Denise", 32, "Things"));
		myEmps.add(new Employee(113, "Edith" , 65, "Things"));
		myEmps.add(new Employee(114, "Frank" ,  7, "Things"));
		
		System.out.println("\nBefore Sort");
		for(Employee e: myEmps) {
			System.out.println(e);
		}
		
		myEmps.sort(new EmpIdComparator());		/* sort by ID */
		System.out.println("\nAfter ID Sort");
		for(Employee e: myEmps) {
			System.out.println(e);
		}
		
		//myEmps.sort(new EmpNameComparator());		/* sort by Name */
		System.out.println("\nAfter Name Sort");
		for(Employee e: myEmps) {
			System.out.println(e);
		}
	}
}
