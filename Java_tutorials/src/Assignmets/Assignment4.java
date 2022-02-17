package Assignmets;

import java.util.Arrays;
import java.util.Scanner;

public class Assignment4 {

	public static void main(String[] args) {
		String[] brands = { "lenovo", "hp", "samsung", "acer", "dell", "asus" };
		String[] intel = { "core i3", "core i5", "core i7", "core i9" };
		double[][] prices = { { 230.21, 400.21, 294.2, 693.33, 340.44, 691.99 },
				{ 529.483, 920.483, 676.66, 1594.659, 783.012, 1591.577 },
				{ 552.504, 960.504, 706.08, 1663.992, 817.056, 1660.776 },
				{ 690.63, 1200.63, 882.6, 2079.99, 1021.32, 2075.97 } };

		Scanner input = new Scanner(System.in);
		String brand;
		String specs;
		double price;
		int[] index = new int[2];

		// ask the user for input and return the index of the input value in array
		while (true) {
			System.out.print("Enter brand: ");
			brand = input.nextLine().toLowerCase();
			System.out.print("Enter specs: ");
			specs = input.nextLine().toLowerCase();

			var contain = Arrays.stream(brands).anyMatch(brand::equals) && Arrays.stream(intel).anyMatch(specs::equals);
			if (contain) {
				index[0] = Arrays.asList(intel).indexOf(specs);
				index[1] = Arrays.asList(brands).indexOf(brand);
				break;
			} else
				System.out.println("Please enter a valid brand or specs: ");
		}
		price = prices[index[0]][index[1]];

		// System.out.println("$"+price);
		System.out.print("Enter exchange rate: ");
		double exchangeRate = input.nextDouble();
		System.out.printf("Price: %,d MMK", Math.round(price * exchangeRate));
		input.close();
	}

}
