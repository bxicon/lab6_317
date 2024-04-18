package lab6_317;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
	private Scanner scanner;
	private User user;
	private UtilityCompany uc;
	
	public ATM(User user, UtilityCompany uc) {
		scanner = new Scanner(System.in);
		this.user = user;
		this.uc = uc;
	}
	
	//Starts the ATM for the user to give input
	public void start() {
		System.out.println("Welcome to the ATM!");
		
		//Allows user to choose what account to access
		while(true) {
			System.out.println("\nPlease choose an account:");
			System.out.println("1: Checking Account");
			System.out.println("2: Saving Account");
			System.out.println("3: The Utility Company");
			System.out.println("4: Exit");
			int choice = getIntInput();
			switch(choice) {
				case 1:
					checkingAccount();
					break;
				case 2:
					savingAccount();
					break;
				case 3:
					utilityCompany();
					break;
				case 4:
					System.out.println("Thank you for using the ATM, Goodbye!");
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		}
	}
	
	//Allows user to interact with the Checking Account
	private void checkingAccount() {
		System.out.println("You have selected your Checking Account!");
		
		while (true) {
			System.out.println("\nPlease choose an option: ");
			System.out.println("1: Deposit: ");
			System.out.println("2: Withdraw: ");
			System.out.println("3: Transfer to Saving Account: ");
			System.out.println("4: Check Balance: ");
			System.out.println("5: Return to Account Selection");
			int choice = getIntInput();
			switch(choice) {
				//Allows user to deposit money into the Checking Account
				case 1:
					System.out.println("Enter amount to deposit into Checking Account: ");
					double amount1 = getDoubleInput();
					if(user.getCheckingAccount().deposit(amount1))
						System.out.println("Deposit successful!");
					else
						System.out.println("Deposit unsuccessful.");
					
					break;
				//Allows user to withdraw money from the Checking Account
				case 2:
					System.out.println("Enter amount to withdraw from Checking Account: ");
					double amount2 = getDoubleInput();
					if(user.getCheckingAccount().withdraw(amount2))
						System.out.println("Withdrawal successful!");
					else
						System.out.println("Withdrawal unsuccessful.");
					
					break;
				//Allows user to transfer money from Checking Account to Saving Account
				case 3:
					System.out.println("Enter amount to transfer from Checking Account to Saving Account: ");
					double amount3 = getDoubleInput();
					if(user.getCheckingAccount().transfer(user.getSavingAccount(), amount3))
						System.out.println("Transfer successful!");
					else
						System.out.println("Transfer unsuccessful.");
					
					break;
				//Allows user to see balance of Checking Account
				case 4:
					System.out.println("Current Checking Balance: $" + user.getCheckingAccount().getBalance());
					break;
				//Exit Checking Account Options
				case 5:
					System.out.println("Returning to Account Selection.");
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
					
			}
		}
	}
	
	//Allows the user to interact with the Saving Account
	private void savingAccount() {
		System.out.println("You have selected your Saving Account!");
		
		while (true) {
			System.out.println("\nPlease choose an option: ");
			System.out.println("1: Deposit: ");
			System.out.println("2: Transfer to Checking Account: ");
			System.out.println("3: Check Balance: ");
			System.out.println("4: Return to Account Selection");
			int choice = getIntInput();
			switch(choice) {
				//Allows user to deposit money into Saving Account
				case 1:
					System.out.println("Enter amount to deposit into Saving Account: ");
					double amount1 = getDoubleInput();
					if(user.getSavingAccount().deposit(amount1))
						System.out.println("Deposit successful!");
					else
						System.out.println("Deposit unsuccessful.");
					
					break;
				//Allows user to transfer money from Saving Account to Checking Account
				case 2:
					System.out.println("Enter amount to transfer from Saving Account to Checking Account: ");
					double amount2 = getDoubleInput();
					if(user.getSavingAccount().transfer(user.getCheckingAccount(), amount2))
						System.out.println("Transfer successful!");
					else
						System.out.println("Transfer unsuccessful.");
					
					break;
				//Allows user to check Saving Account balance
				case 3:
					System.out.println("Current Saving Balance: $" + user.getSavingAccount().getBalance());
					break;
				case 4:
					System.out.println("Returning to Account Selection.");
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
					
			}
		}
	}
	
	//Allows user to interact with Utility Company
	private void utilityCompany() {
		System.out.println("You have selected the Utility Company!");
		
		while (true) {
			System.out.println("\nPlease choose an option: ");
			System.out.println("1: Sign Up: ");
			System.out.println("2: Log In: ");
			System.out.println("3: Return to Account Selection");
			int choice = getIntInput();
			switch(choice) {
			//Case to sign up
				case 1:
					System.out.println("Please enter a username for your utility account: ");
					scanner.nextLine();
					String username = scanner.nextLine();
					System.out.println("Please enter a password for your utility account: ");
					String password = scanner.nextLine();
					
					int accountNumber = uc.createAccount(username, password, user);
					
					//If createAccount ran correctly
					if(accountNumber != -1) {
						System.out.println("Utility account created successfully!");
						System.out.println("Your account number is: " + String.format("%06d", accountNumber));
					}
					else
						System.out.println("Failed to create utility account. Username may already be taken.");
					
					break;
				//Case to log in
				case 2:
					System.out.println("Please enter your utility account number or username: ");
					scanner.nextLine();
					String username1 = scanner.nextLine();
					System.out.println("Please enter your utility account password:");
				    String password1 = scanner.nextLine();
				    
				    UtilityAccount account = uc.login(username1, password1);
				    
				    //If login ran correctly
				    if(account != null)
				    	utilityAccount(account);
				    else
				    	System.out.println("Login failed. Please check your username/password or account number.");
					
					break;
				case 3:
					System.out.println("Returning to Account Selection.");
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		}
	}
	
	//Allows user to interact with utility account
	private void utilityAccount(UtilityAccount account) {
		System.out.println("Logged in successfully to utility account!");
		
		while (true) {
			System.out.println("\nPlease choose an option: ");
			System.out.println("1: Check Bill Payment History: ");
			System.out.println("2: Check Next Bill Payment Amount & Date: ");
			System.out.println("3: Pay Bill: ");
			System.out.println("4: Return to Utility Company Registration");
			int choice = getIntInput();
			switch(choice) {
				//Case to check bill payment history
				case 1:
					System.out.println("Recent Bill Payments: " + account.getBillPaymentHistory());
					break;
				//Case to check next bill payment amount and date
				case 2:
					System.out.println("Next Bill Amount: $" + account.getNextBillAmount());
					System.out.println("Due Date: " + account.getNextBillDate());
					break;
				//Case to pay bill with Checking Account
				case 3:
					System.out.println("Enter the amount to pay for your utility bill: ");
					double amount = getDoubleInput();
					if(account.payBill(amount))
						System.out.println("Bill paid successfully!");
					else
						System.out.println("Bill payment failed.");
						
					break;
				case 4:
					System.out.println("Returning to Utility Company Registration.");
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		}
	}
	
	//Method to get an integer from scanner and not allowing any other input
    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine(); 
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    
  //Method to get a double from scanner and not allowing any other input
    private double getDoubleInput() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                scanner.nextLine(); 
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
	
	
}
