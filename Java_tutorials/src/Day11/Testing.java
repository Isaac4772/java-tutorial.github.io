package Day11;

import java.util.Arrays;
import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		Student[] data = new Student[4];

		// Initialize data
		initializeData(data);

		System.out.println(data);
		// display
		displayData(data);

		// search
		Scanner input = new Scanner(System.in);
		System.out.println("Enter student id to search: ");
		searchData(input.nextInt(), data);

		// findMaximum
		findMax(data);

		//
		findAverage(data);

	}

	private static void findAverage(Student[] data) {

	}

	private static void findMax(Student[] data) {
		int index = -1;
		int max = -1;
		for (var i = 0; i < data.length; i++) {
			if (data[i].getMark() > max) {
				max = data[i].getMark();
				index = i;
			}
		}
		data[index].display();
	}

	private static void searchData(int id, Student[] data) {
//		for (var std : data) {
//			if (id == std.getStudentId()) {
//				std.display();
//				break;
//			} else
//				System.out.println("Student id " + id + " is not found!");
//		}
		int index = Arrays.binarySearch(data, id);
		data[index].display();

	}

	private static void displayData(Student[] data) {
		for (var std : data) {
			std.display();
		}
	}

	private static void initializeData(Student[] data) {

		try (Scanner input = new Scanner(System.in)) {
			for (var x = 0; x < data.length; x++) {
				System.out.print("Enter Id: ");
				data[x].setStudentId(input.nextInt());
				System.out.print("Enter name: ");
				data[x].setName(input.nextLine());
				System.out.println("Enter mark: ");
				data[x].setMark(input.nextInt());
			}
		}
	}
}
