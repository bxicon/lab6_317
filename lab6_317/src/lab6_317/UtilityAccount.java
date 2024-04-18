package lab6_317;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class UtilityAccount{
	private String username;
	private String password;
	private int accountNumber;
	private Queue<Double> billHistory;
	private double nextBillAmount = 400; 
	private String nextBillDate;
	private User user;
	//Used to format date correctly to M/dd/yyyy
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/dd/yyyy");
	
	public UtilityAccount(String username, String password, int accountNumber, User user) {
		this.username = username;
		this.password = password;
		this.accountNumber = accountNumber;
		this.user = user;
		this.billHistory = new LinkedList<>();
		//Initializes nextBillDate to 7 days from today
		updateNextBillDate();
	}
	
	public boolean payBill(double amount) {
		//If amount does not exceed the bill amount
		if(amount <= nextBillAmount) {
			//If the user can pay the bill from the Checking Side
			if(user.getCheckingAccount().payBill(amount)) {
				//Add amount to the bill history and truncate it to the most recent 3 transactions
				billHistory.add(amount);
				if(billHistory.size() > 3) {
					billHistory.poll();
				}
				//Reduce the bill by how much was paid off
				nextBillAmount = nextBillAmount - amount;
				return true;
			}
		}
		return false;
	}
	
	//Updates nextBillDate to be todays date + a week
	private void updateNextBillDate() {
		LocalDate today = LocalDate.now();
		LocalDate nextBill = today.plusDays(7);
		nextBillDate = nextBill.format(DATE_FORMATTER);
	}
	
	//Getters
	
	public Queue<Double> getBillPaymentHistory(){
		return new LinkedList<>(billHistory);
	}
	
	public double getNextBillAmount() {
		return nextBillAmount;
	}
	
	public String getNextBillDate() {
		return nextBillDate;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
}
