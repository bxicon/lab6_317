package lab6_317;

import java.util.LinkedList;
import java.util.Queue;

public class UtilityAccount{
	private String username;
	private String password;
	private int accountNumber;
	private Queue<Double> billHistory;
	private double nextBillAmount = 400; //Test value
	private String nextBillDate = "4/30/2024"; //Test value
	private User user;
	
	public UtilityAccount(String username, String password, int accountNumber, User user) {
		this.username = username;
		this.password = password;
		this.accountNumber = accountNumber;
		this.user = user;
		this.billHistory = new LinkedList<>();
	}
	
	public boolean payBill(double amount) {
		if(amount <= nextBillAmount) {
			if(user.getCheckingAccount().payBill(amount)) {
				billHistory.add(amount);
				if(billHistory.size() >= 3) {
					billHistory.poll();
				}
				nextBillAmount = nextBillAmount - amount;
				return true;
			}
		}
		return false;
	}
	
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
