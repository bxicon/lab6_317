package lab6_317;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class UtilityCompany {
	private Map<String, UtilityAccount> accounts;
	private Set<Integer> usedAccountNumbers;
	
	public UtilityCompany() {
		accounts = new HashMap<>();
		usedAccountNumbers = new HashSet<>();
	}
	
	public boolean createAccount(String username, String password, User user) {
		if(accounts.containsKey(username))
			return false;
		
		int accountNumber = generateAccountNumber();
		accounts.put(username, new UtilityAccount(username, password, accountNumber, user));
		usedAccountNumbers.add(accountNumber);
		return true;
	}
	
	private int generateAccountNumber() {
		Random rand = new Random();
		int number;
		do {
			number = rand.nextInt(999999);
		} while (usedAccountNumbers.contains(number));
		return number;
	}
	
	
}
