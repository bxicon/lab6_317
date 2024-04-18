package lab6_317;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		//User test = new User();
		//DataController.saveUser(test);
		
		//UtilityCompany testUC = new UtilityCompany();
		//DataController.saveUtilityCompany(testUC);
		
		//Loads user from json file
		User user = DataController.loadUser();
		//Loads uc from json file
		UtilityCompany uc = DataController.loadUtilityCompany();
		//Start the ATM with given user and uc
		ATM atm = new ATM(user, uc);
		atm.start();
		//Save the updated user and uc to their json files
		DataController.saveUser(user);
		DataController.saveUtilityCompany(uc);
	}
}
