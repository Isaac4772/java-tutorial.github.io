package ClassAndObjectsAssignment;

import java.util.Scanner;

public class Bank_Account {
	private int accountNo;
	private String holderName;
	private String pinNo;
	private String password;
	private int balance = 1000;

	public Bank_Account() {

	}

	public Bank_Account(int accountNo, String holderName, String pinNo, String password, int balance) {
		super();
		this.accountNo = accountNo;
		this.holderName = holderName;
		this.pinNo = pinNo;
		this.password = password;
		this.balance = balance;
	}

	void checkAmount(int amount) throws InsufficientAmountException {
		if (amount > this.balance) {
			throw new InsufficientAmountException("Insufficient balance!");
		}
	}

	void checkPin(String pin) throws PinNotMatchException {
		if (!pin.equals(this.pinNo))
			throw new PinNotMatchException("Invalid pin number! ");
	}

	void deposit(int amount) {
		this.balance += amount;
		System.out.println("Deposit compeleted!");
	}

	void withdraw(int amount) {
		try {
			checkAmount(amount);
			this.balance -= amount;
			System.out.println("Withdrawal compeleted!");
		} catch (InsufficientAmountException e) {
			System.err.println(e.getMessage());
		}
	}

	void changePassword(Scanner input) {
		System.out.print("Enter your pin Number: ");
		String pin = input.nextLine();
		try (input) {
			checkPin(pin);
			System.out.print("Enter a new password: ");
			this.password = input.next();
			System.out.println("Password is suceessfully changed!");
		} catch (PinNotMatchException e) {
			System.err.println(e.getMessage());
		}
	}

	void showInfo() {
		System.out.println("\n____Account Information____");
		System.out.println("Account No: " + accountNo);
		System.out.println("Holder Name: " + holderName);
		System.out.println("Balance: " + balance);
		System.out.println("Pin No: " + pinNo);
		System.out.println("Password: " + password + "\n");
	}

}

class InsufficientAmountException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientAmountException() {

	}

	public InsufficientAmountException(String msg) {
		super(msg);
	}

}

class PinNotMatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PinNotMatchException() {

	}

	public PinNotMatchException(String msg) {
		super(msg);
	}
}
