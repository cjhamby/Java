package tutorialRepo.banks;

public final class PublicSectorBank extends Bank {
	private int lockerFacility = 0;
	private float mutualFunds = 0;
	
	public PublicSectorBank() {
		System.out.println("public bank created");
	}
	
	public void print() {
		System.out.println("public bank");
	}
}