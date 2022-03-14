package Day7;

import java.util.Scanner;

public class Try_finally {
	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(System.in);
			System.out.print("Enter Salary: ");
			int salary = Integer.parseInt(input.nextLine());
			
			if(salary == 0)
				return;
			System.out.println("Your salary: " + salary);
		} finally {
			input.close();
			System.out.println("This is finally block");
		}
		System.out.println("Outside try finally block");
	}
}
