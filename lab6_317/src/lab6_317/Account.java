package lab6_317;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class Account {
	//Balance of the account
	protected double balance;
	//Daily amount of money that is allowed to be deposited
	protected double dailyDepositLimit = 5000;
	//Amount of money deposited so far in day
	protected double dailyDepositTotal = 0;
	//Date the user last interacted with ATM
	protected String lastInteractionDateString;
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
	
	//Deposit amount to account if dailyDepositLimit not reached
	public boolean deposit(double amount) {
		resetIfNewDay();
		if(amount + dailyDepositTotal <= dailyDepositLimit) {
			balance += amount;
			dailyDepositTotal += amount;
			return true;
		}
		return false;
	}
	
	//Used to reset totals if it is a new day
	protected abstract void resetIfNewDay();
	
	//Transfer money from one account to another
	public abstract boolean transfer(Account acc, double amount);
	
	//Get balance of account
	public double getBalance() {
		return balance;
	}
	
	//Updates the last time the user interacted with the ATM
	protected void updateLastInteractionDate() {
		lastInteractionDateString = LocalDate.now().format(DATE_FORMATTER);
	}
	
	//Gets the last time the user interacted with the ATM
	protected LocalDate getLastInteractionDate() {
		if(lastInteractionDateString == null)
			return null;
		
		return LocalDate.parse(lastInteractionDateString, DATE_FORMATTER);
	}
}

class CheckingAccount extends Account{
	//Daily amount of money that is allowed to be withdrawn
	private double dailyWithdrawalLimit = 500;
	//Amount of money withdrawn so far in day
	private double dailyWithdrawalTotal = 0;
	
	//Withdraw amount from account if dailyWithdrawalLimit not reached and balance does not go negative
	public boolean withdraw(double amount) {
		resetIfNewDay();
		if(amount + dailyWithdrawalTotal <= dailyWithdrawalLimit && amount <= balance) {
			balance -= amount;
			dailyWithdrawalTotal += amount;
			return true;
		}
		return false;
	}
	
	//Transfer amount from account to another account as long as balance does not go negative
	@Override
	public boolean transfer(Account acc, double amount) {
		if(amount <= balance) {
			balance -= amount;
			acc.balance += amount;
			return true;
		}
		return false;
	}
	
	//Pay bill to utility company as long as balance does not go negative TODO
	public boolean payBill(double amount) {
		if(amount <= balance) {
			balance -= amount;
			return true;
		}
		return false;
	}
	
	//Resets dailyDepositTotal and dailyWithdrawalTotal if it has been more than a day since last interaction
	@Override
	protected void resetIfNewDay() {
		LocalDate today = LocalDate.now();
		LocalDate lastInteractionDate = getLastInteractionDate();
		if(lastInteractionDate == null || !lastInteractionDate.equals(today)) {
			dailyDepositTotal = 0;
			dailyWithdrawalTotal = 0;
			updateLastInteractionDate();
		}
	}
}

class SavingAccount extends Account{
	//Daily amount of money that is allowed to be transferred
	private double dailyTransferLimit = 100;
	//Amount of money transferred so far in day
	private double dailyTransferTotal = 0;
	
	//Transfer amount from account to another account as long as balance does not go negative and if dailyTransferLimit is not reached
	@Override
	public boolean transfer(Account acc, double amount) {
		resetIfNewDay();
		if(amount + dailyTransferTotal <= dailyTransferLimit && amount <= balance) {
			balance -= amount;
			acc.balance += amount;
			dailyTransferTotal += amount;
			return true;
		}
		return false;
	}
	
	//Resets dailyDepositTotal and dailyTransferTotal if it has been more than a day since last interaction
	@Override
	protected void resetIfNewDay() {
		LocalDate today = LocalDate.now();
		LocalDate lastInteractionDate = getLastInteractionDate();
		if(lastInteractionDate == null || !lastInteractionDate.equals(today)) {
			dailyDepositTotal = 0;
			dailyTransferTotal = 0;
			updateLastInteractionDate();
		}
	}
}