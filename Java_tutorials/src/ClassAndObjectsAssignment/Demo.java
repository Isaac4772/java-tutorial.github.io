package ClassAndObjectsAssignment;

import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Bank_Account obj1 = new Bank_Account(1001, "Aung Paing", "1234", "aungpaing47", 10000000);

		obj1.showInfo();

		obj1.deposit(100000);

		obj1.withdraw(1020000);

		obj1.changePassword(input);

		obj1.showInfo();
	}

}
