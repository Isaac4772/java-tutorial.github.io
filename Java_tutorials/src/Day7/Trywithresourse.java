package Day7;
import java.util.Scanner;
public class Trywithresourse {

	public static void main(String[] args) {
		
		try(Scanner input = new Scanner(System.in)){
			System.out.print("Enter your salary: ");
			int salary = Integer.parseInt(input.nextLine());
			System.out.println("salary: " + salary);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

}
