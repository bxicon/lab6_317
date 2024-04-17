package lab6_317;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		//User test = new User();
		//DataController.saveUser(test);
		
		//UtilityCompany testUC = new UtilityCompany();
		//DataController.saveUtilityCompany(testUC);
		
		User user = DataController.loadUser();
		UtilityCompany uc = DataController.loadUtilityCompany();
		ATM atm = new ATM(user, uc);
		atm.start();
		DataController.saveUser(user);
		DataController.saveUtilityCompany(uc);
	}
}
