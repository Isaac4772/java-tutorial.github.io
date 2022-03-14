package Assignmets;

import java.util.Arrays;
import java.util.Scanner;

public class Assignment7 {

	public static void main(String[] args) {
		String paragraph = "NLP techniques are becoming widely popular scientific research areas as well as Information Technology industry. Language technology together with Information Technology can enhance the lives of people with different capabilities. This system implements voice command mobile phone dialer application. The strength of the system is that it can make phone call to the contact name written in either English scripts or Myanmar scripts.";
		String[] words = paragraph.split(" ");
		System.out.println("Total words: " + words.length);
		String[] sentences = paragraph.split("\\.");
		System.out.println("Number of sentences: " + sentences.length);

		Scanner input = new Scanner(System.in);
		System.out.println("Enter a sentence below: ");
		String sentence = input.nextLine();
		System.out.println(sentence.charAt(sentence.length() - 1));

		String[] presentTense = { "am ", "is ", "are ", "do ", "does ", "isn't ", "aren't ", "don't ", "doesn't " };
		String[] pastTense = { "was ", "were ", "did ", "wasn't ", "weren't " };

		if (sentence.charAt(sentence.length() - 1) == '?') {
			System.out.println("It is a question");
			var lowSentence = sentence.toLowerCase();
			boolean found = false;
			for (String word : presentTense) {
				found = lowSentence.contains(word);
				if (found == true) {
					System.out.println("It is present tense.");
					break;
				}
			}
			if (found == false) {
				for (String word : pastTense) {
					found = lowSentence.contains(word);
					if (found == true) {
						System.out.println("It is past tense");
						break;
					}
				}
			}
		} else
			System.out.println("It is not a question");

		System.out.print("NRC No: ");
		String nrcno = input.nextLine();
		input.close();
		System.out.println("Township: " + nrcno.substring(nrcno.indexOf("/") + 1, nrcno.indexOf("(")));
		System.out.println("Number: " + nrcno.substring(nrcno.lastIndexOf(")") + 1));
		
	}

}
