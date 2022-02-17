package Assignmets;

import java.util.Scanner;

public class Assignment1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Name: ");
		String name = input.nextLine();
		System.out.print("Email: ");
		String email = input.nextLine();
		System.out.print("Phone: ");
		String phone = input.nextLine();
		System.out.print("Education: ");
		String education = input.nextLine();
		System.out.print("Income: ");
		int income = input.nextInt();
		System.out.print("Age: ");
		int age = input.nextInt();
		input.close();
		
		System.out.println("Name: " + name);
		System.out.println("Email: " + email);
		System.out.println("Phone: " + phone);
		System.out.println("Education: " + education);
		System.out.println("Income: " + income);
		System.out.println("Age: " + age);
	}

}
