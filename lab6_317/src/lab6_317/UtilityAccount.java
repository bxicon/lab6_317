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
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/dd/yyyy");
	
	public UtilityAccount(String username, String password, int accountNumber, User user) {
		this.username = username;
		this.password = password;
		this.accountNumber = accountNumber;
		this.user = user;
		this.billHistory = new LinkedList<>();
		updateNextBillDate();
	}
	
	public boolean payBill(double amount) {
		if(amount <= nextBillAmount) {
			if(user.getCheckingAccount().payBill(amount)) {
				billHistory.add(amount);
				if(billHistory.size() > 3) {
					billHistory.poll();
				}
				nextBillAmount = nextBillAmount - amount;
				return true;
			}
		}
		return false;
	}
	
	private void updateNextBillDate() {
		LocalDate today = LocalDate.now();
		LocalDate nextBill = today.plusDays(7);
		nextBillDate = nextBill.format(DATE_FORMATTER);
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
