package lab6_317;

public class User {
	//Users Checking Account and Saving Account
	private CheckingAccount checkingAccount;
	private SavingAccount savingAccount;
	
	//Constructor
	public User() {
		checkingAccount = new CheckingAccount();
		savingAccount = new SavingAccount();
	}
	
	//Getters
	
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}
	
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}
}
