package tutorialRepo.banks;

public class Bank {
	protected static final double minBalance = 5.0;
	protected float balance = 0;
	
	public Bank() {
		System.out.println("bank created");
	}
	
	public boolean deposit(float amt) {
		balance+=amt;
		return true;
	};
	
	public boolean withdraw(float amt) {
		if(amt > balance) {
			return false;
		}
		balance -= amt;
		return true;
	};
	
	public void chargeInterest() {
		balance *= 0.3;	/* 70% interest rates are cool */
	}
	
	public void print() {
		System.out.println("bank");
	}
	
	
}



