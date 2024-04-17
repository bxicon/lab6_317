package lab6_317;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class UtilityCompany {
	private Map<String, UtilityAccount> accountsByUsername;
	private Map<Integer, UtilityAccount> accountsByNumber;
	
	public UtilityCompany() {
		accountsByUsername = new HashMap<>();
		accountsByNumber = new HashMap<>();
	}
	
	public int createAccount(String username, String password, User user) {
		if(accountsByUsername.containsKey(username))
			return -1;
		
		int accountNumber = generateAccountNumber();
		UtilityAccount newAccount = new UtilityAccount(username, password, accountNumber, user);
		accountsByUsername.put(username, newAccount);
		accountsByNumber.put(accountNumber, newAccount);
		return accountNumber;
	}
	
	public UtilityAccount login(String identifier, String password) {
		UtilityAccount account = null;
		
		try {
			int accountNumber = Integer.parseInt(identifier);
			account = accountsByNumber.get(accountNumber);
		}
		catch(NumberFormatException e) {
			account = accountsByUsername.get(identifier);
		}
		
		if(account != null && account.getPassword().equals(password))
			return account;
		
		return null;
	}
	
	private int generateAccountNumber() {
		Random rand = new Random();
		int number;
		do {
			number = rand.nextInt(999999);
		} while (accountsByNumber.containsKey(number));
		return number;
	}
	
	
}
