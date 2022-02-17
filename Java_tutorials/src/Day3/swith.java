package Day3;

import java.util.Scanner;

public class swith {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		input.close();
		System.out.print("Enter food name: ");
		String name = input.nextLine();
		
		//single statemet
		var category = 
		switch (name) {
		case "burger", "pizza", "sandwich" -> "Fast Food";
		case "yogurt", "milk tea" -> "Dessert";
		case "mohinga" -> "Burmese Food";
		case "sushi" -> "Japanese Food";
		default -> "unknown";
		};
		
		//multiple statemets
		var category1 = 
		switch (name) {
		case "burger", "pizza", "sandwich" -> {
			if(name.equals("pizza")) 
				System.out.println(name+ " is an Italian Food");
				yield "Fast Food";
		}
		case "yogurt", "milk tea" -> "Dessert";
		case "mohinga" -> "Burmese Food";
		case "sushi" -> "Japanese Food";
		default -> "unknown";
		};
		System.out.println(category);
		System.out.println(category1);
	}

}
