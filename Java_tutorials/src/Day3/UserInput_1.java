package Day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter name: ");
		String name = reader.readLine();
		System.out.print("Enter salary: ");
		double salary = Double.parseDouble(reader.readLine());
		System.out.print("Enter age: ");
		int age = Integer.parseInt(reader.readLine());
		reader.close();
		
		System.out.println("Name: " + name);
		System.out.println("Salary: " + salary);
		System.out.println("Age: " + age);
	}

}
