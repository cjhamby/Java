package tutorialRepo.banks;

public class test {

	public static void main(String[] args) {
		
		/* valid */
		Bank b1 = new PublicSectorBank();
		
		/* valid */
		PublicSectorBank b2 = new PublicSectorBank();
		
		System.out.println("\nTypes of Banks:");
		(b1).print();
		(b2).print();
	}

}
