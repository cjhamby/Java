/* Collections use Comparators for the sort() method 
 * The Comparator determines what is sorted (name, id, etc)
 * 
 * This is an ID comparator 
 */

package employees;

import java.util.Comparator;

public class EmpIdComparator implements Comparator<Employee> {
	
	/* compare based on the employee id field */
	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getEmpId() - o2.getEmpId();
	}
}
