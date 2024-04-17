package lab6_317;

public class User {
	private CheckingAccount checkingAccount;
	private SavingAccount savingAccount;
	
	public User() {
		checkingAccount = new CheckingAccount();
		savingAccount = new SavingAccount();
	}
	
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}
	
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}
}
