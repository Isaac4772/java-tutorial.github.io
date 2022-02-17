package Assignmets;

import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("How many numbers you want to type: ");
		int num = input.nextInt();
		int positive = 0;
		int negative = 0;
		int zero = 0;
				
		for (int i = 0; i < num; i++) {
			System.out.print("Enter a number: ");
			int inputNum = input.nextInt();
			if (inputNum < 0)
				negative++;
			else if (inputNum > 0)
				positive++;
			else
				zero++;
		}
		
		input.close();
		System.out.println("Numbers of zero: " + zero);
		System.out.println("Numbers of positive number" + positive);
		System.out.println("Numbers of negative number" + negative);

	}
}
