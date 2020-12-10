package worksheet;

public class PassByVal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sId = 25;
		PassByVal val = new PassByVal();
		System.out.println("sId before method = " + sId);
		val.passTheValueMethod(sId);
		System.out.println("sId after method = " + sId);
		
	}
	
	public void passTheValueMethod(int sId) {
		sId = 10;
		System.out.println("sId in method = " + sId);
	}

}
