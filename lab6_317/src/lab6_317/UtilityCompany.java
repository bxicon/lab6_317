package lab6_317;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class UtilityCompany {
	//Map the accounts by the username of the accounts
	private Map<String, UtilityAccount> accountsByUsername;
	//Maps the accounts by the unique account number
	private Map<Integer, UtilityAccount> accountsByNumber;
	
	public UtilityCompany() {
		accountsByUsername = new HashMap<>();
		accountsByNumber = new HashMap<>();
	}
	
	public int createAccount(String username, String password, User user) {
		//If username is already taken, return -1
		if(accountsByUsername.containsKey(username))
			return -1;
		
		//Generates unique 6 digit account number for account
		int accountNumber = generateAccountNumber();
		//Creates new account
		UtilityAccount newAccount = new UtilityAccount(username, password, accountNumber, user);
		//Add new account to the two mappings
		accountsByUsername.put(username, newAccount);
		accountsByNumber.put(accountNumber, newAccount);
		
		return accountNumber;
	}
	
	public UtilityAccount login(String identifier, String password) {
		UtilityAccount account = null;
		
		//Try to log in by acting as if identifier is the account number
		try {
			int accountNumber = Integer.parseInt(identifier);
			account = accountsByNumber.get(accountNumber);
		}
		catch(NumberFormatException e) {
			//If exception, that means not account number, try using as username
			account = accountsByUsername.get(identifier);
		}
		
		//If account is not empty and the password is correct, return the account
		if(account != null && account.getPassword().equals(password))
			return account;
		
		return null;
	}
	
	//Generates random unique account number from 0 to 999999
	private int generateAccountNumber() {
		Random rand = new Random();
		int number;
		do {
			number = rand.nextInt(999999);
		} while (accountsByNumber.containsKey(number));
		return number;
	}
	
	
}
