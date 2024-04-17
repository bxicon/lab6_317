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
	
	public void start() {
		System.out.println("Welcome to the ATM!");
		
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
					//utilityCompany();
					break;
				case 4:
					System.out.println("Thank you for using the ATM, Goodbye!");
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		}
	}
	
	private void checkingAccount() {
		System.out.println("You have selected your Checking Account!");
		
		while (true) {
			System.out.println("\nPlease choose an option: ");
			System.out.println("1: Deposit: ");
			System.out.println("2: Withdraw: ");
			System.out.println("3: Transfer to Saving Account: ");
			System.out.println("4: Pay Bill: ");
			System.out.println("5: Check Balance: ");
			System.out.println("6: Return to Account Selection");
			int choice = getIntInput();
			switch(choice) {
				case 1:
					System.out.println("Enter amount to deposit into Checking Account: ");
					double amount1 = getDoubleInput();
					if(user.getCheckingAccount().deposit(amount1))
						System.out.println("Deposit successful!");
					else
						System.out.println("Deposit unsuccessful.");
					
					break;
				case 2:
					System.out.println("Enter amount to withdraw from Checking Account: ");
					double amount2 = getDoubleInput();
					if(user.getCheckingAccount().withdraw(amount2))
						System.out.println("Withdrawal successful!");
					else
						System.out.println("Withdrawal unsuccessful.");
					
					break;
				case 3:
					System.out.println("Enter amount to transfer from Checking Account to Saving Account: ");
					double amount3 = getDoubleInput();
					if(user.getCheckingAccount().transfer(user.getSavingAccount(), amount3))
						System.out.println("Transfer successful!");
					else
						System.out.println("Transfer unsuccessful.");
					
					break;
				case 4:
					System.out.println("TODO");
					break;
				case 5:
					System.out.println("Current Checking Balance: $" + user.getCheckingAccount().getBalance());
					break;
				case 6:
					System.out.println("Returning to Account Selection.");
					return;
				default:
					System.out.println("Invalid selection. Please try again.");
					
			}
		}
	}
	
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
				case 1:
					System.out.println("Enter amount to deposit into Saving Account: ");
					double amount1 = getDoubleInput();
					if(user.getSavingAccount().deposit(amount1))
						System.out.println("Deposit successful!");
					else
						System.out.println("Deposit unsuccessful.");
					
					break;
				case 2:
					System.out.println("Enter amount to transfer from Saving Account to Checking Account: ");
					double amount2 = getDoubleInput();
					if(user.getSavingAccount().transfer(user.getCheckingAccount(), amount2))
						System.out.println("Transfer successful!");
					else
						System.out.println("Transfer unsuccessful.");
					
					break;
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
