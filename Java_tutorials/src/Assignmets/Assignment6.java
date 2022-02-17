package Assignmets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Assignment6 {

	public static void showChoices(String[] choices) {
		var index = 1;
		for (String item : choices) {
			System.out.println(index + ". " + item);
			index++;
		}
	}

	public static void showChoices(String[] choices, int[] times) {
		var index = 1;
		for (String item : choices) {
			System.out.println(index + ". " + item + " (" + times[index - 1] + " min)");
			index++;
		}
	}

	public static int choose(String[] choices, Scanner input) {
		System.out.print("Please choose item: ");
		int item;
		while (true) {
			item = input.nextInt();
			if (item <= choices.length && item > 0)
				break;
			else
				System.out.println("Please choose what is shown: ");
		}
		return item;
	}
	
	public static int chooseOrder(Scanner input) {
		int deliveryDate;
		System.out.println("\n***** Order now? *****");
		System.out.println("1. OrderNow?\n2. Preorder?");
		System.out.println("Please choose 1 or 2: And order type will be chosen.");
		while(true) {
			int order = input.nextInt();
			if(order == 1)
				return order;
			else if(order == 2) {
				System.out.print("Enter delivery date (dd): ");
				deliveryDate = input.nextInt();
				return deliveryDate;
			}
			
		}
		
		
	}

	public static String calculateDuration(int order, int time) {
		String duration;
		if(order == 1) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss a");
			duration = LocalTime.now().plusMinutes(time).format(format);
		return duration;
		}
		else {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy");
			duration = LocalDate.now().plusDays(order).format(format);
		return duration;
		}
	}

	public static void showOrderInfo(int order, String name, String address, int duration, String time) {
		System.out.println("\n***** Your Order Information *****");
		System.out.println("Item Name: " + name);
		System.out.println("Your Address: " + address);
		if(order == 1)
			System.out.println("Duration: " + duration + " min");
		System.out.println("Arrival Time: " + time);
		System.out.println("******** Thank you for your ordering *******");
	}

	public static void main(String[] args) {
		String[] townships = { "AA", "BB", "CC", "DD" };
		int[] times = { 15, 30, 10, 45 };
		String[] menus = { "Pizza", "Burger", "Milk Tea", "Spicy Noodle" };

		Scanner input = new Scanner(System.in);
//		Menu block
		System.out.println("\n***** Available Menu *****");
		showChoices(menus);
		int food = choose(menus, input);
//		System.out.println(food);

//		Township block
		System.out.println("\n***** Deliverable Township *****");
		showChoices(townships, times);
		int town = choose(townships, input);
//		System.out.println(town);

//		Order block
		int order = chooseOrder(input);
//		System.out.println(order);

//	    Showing order Information block
		String duration = calculateDuration(order, times[town - 1]);
//		System.out.println(duration);
		showOrderInfo(order, menus[food - 1], townships[town - 1], times[town - 1], duration);

		input.close();

	}

}
