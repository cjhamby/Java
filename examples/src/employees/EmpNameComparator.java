/* Collections use Comparators for the sort() method 
 * The Comparator determines what is sorted (name, id, etc)
 * 
 * This is a Name comparator 
 */
package employees;

import java.util.Comparator;

public class EmpNameComparator implements Comparator<Employee> {
	
	/* sort by names */
	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getEmpName().compareTo( o2.getEmpName() );
	}
}
