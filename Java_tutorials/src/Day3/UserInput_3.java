package Day3;

import java.util.Scanner;

public class UserInput_3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter name:");
		String name = input.nextLine();
		System.out.print("Enter salary: ");
		double salary = input.nextDouble();
		System.out.print("Enter age: ");
		int age = input.nextInt();
		input.close();
		
		System.out.println("Name: " + name);
		System.out.println("Salary: " + salary);
		System.out.println("Age: " + age);

	}

}
