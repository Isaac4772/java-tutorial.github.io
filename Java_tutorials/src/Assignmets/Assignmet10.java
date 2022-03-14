package Assignmets;

import java.util.Scanner;

public class Assignmet10 {

	public static void main(String[] args) {
		String[] division = { "AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ", "KK", "LL" };
		try (Scanner input = new Scanner(System.in)) {
			System.out.print("Nrc No: ");
			String nrcno = input.next();
			int len = Integer.parseInt(nrcno.substring(0, nrcno.indexOf("/")));
			System.out.println("Divison/State: " + division[len - 1]);
			System.out.println("City: " + nrcno.substring(nrcno.indexOf("/") + 1, nrcno.indexOf("(")));
			System.out.println("Number: " + nrcno.substring(nrcno.lastIndexOf(")") + 1));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("There's only 12 divisons or states you idiot!!");
		}
		

	}

}
