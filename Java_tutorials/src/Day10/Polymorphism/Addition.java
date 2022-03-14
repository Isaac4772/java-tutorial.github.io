package Day10.Polymorphism;

import java.util.Arrays;

public class Addition {
	static int add(int a, int b) {
		return (a + b);
	}

	static int add(int[] input) {
		return Arrays.stream(input).sum();
	}

	static float add(float a, float b) {
		return (a + b);
	}

	static String add(String a, String b) {
		return (a + b);
	}

	public static void main(String[] args) {
		System.out.println("100 + 200 = " + add(100, 200));
		System.out.println("2.3 + 5.3 = " + add(2.3f, 5.3f));
		System.out.println("Hello + World = " + add("Hello", "World"));
		System.out.println("int array: " + add(new int[] { 10, 20, 30, 40 }));
	}
}
