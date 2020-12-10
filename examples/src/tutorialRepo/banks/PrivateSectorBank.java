package tutorialRepo.banks;

public final class PrivateSectorBank extends Bank {
	private float insurance = 0;
	private float creditCard = 0;
	
	public PrivateSectorBank() {
		System.out.println("private bank created");
	}
	
	public void print() {
		System.out.println("private bank");
	}
}
