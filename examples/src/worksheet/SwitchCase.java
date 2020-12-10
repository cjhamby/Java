package worksheet;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class SwitchCase {
	static char gradeLetter = 'B';
	
	public static void main(String[] args) {
		assignGradeLetter(50);
	}
	
	public static void assignGradeLetter(int grade) {
		switch(grade) {
		case 0: case 1: case 2: case 3: case 4: case 5:
			gradeLetter = 'E';
			break;
		case 6:
			gradeLetter = 'D';
			break;
		case 7:
			gradeLetter = 'C';
			break;
		case 79:
			gradeLetter = 'B';
			break;
		case 100:
			gradeLetter = 'A';
			break;
		default:
			System.out.println("Grade does not exist");
			break;
		}
	}

}
