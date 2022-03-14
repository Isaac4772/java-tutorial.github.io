package Assignmets;

import java.util.Scanner;

public class Assignment11 {

	static void checkMark(int mark) throws OutOfRangeException {
		if (mark < 0 || mark > 100) {
			throw new OutOfRangeException("Mark can't be less than 0 or over 100!");
		}
	}

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.print("Enter student's mark: ");
			int mark = input.nextInt();
			checkMark(mark);
			System.out.println("Student's mark: " + mark);
		} catch (OutOfRangeException e) {
			System.err.println(e.getMessage());
		}
	}

}

class OutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfRangeException() {

	}

	public OutOfRangeException(String msg) {
		super(msg);
	}
}
