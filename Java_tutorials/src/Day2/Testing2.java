package Day2;
import java.util.Scanner;
public class Testing2 {
	
	static final float RATE  = 1.5F;
	static final int MIN_PRICE = 1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.print("Enter price: ");
		int price = input.nextInt();
		if (price < MIN_PRICE) {
			price = MIN_PRICE;
		}
		
		System.out.println("Expense: " + (price * RATE));
		input.close();
	}

}
