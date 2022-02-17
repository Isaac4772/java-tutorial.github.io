package Day4;

import java.util.Arrays;

public class Array_1 {

	public static void main(String[] args) {
		int[] numbers = {100,200,300,400};
		
		for(var n : numbers)
			System.out.println(n);
		
		int[] copyArr1 = Arrays.copyOf(numbers, numbers.length);
		System.out.println("CopyArray: " + Arrays.toString(copyArr1));
		
		int[] copyArr2 = Arrays.copyOfRange(numbers, 1, 3);
		System.out.println("Copied Array2: " + Arrays.toString(copyArr2));
		
		System.out.println("numbers == copyArr1: " + Arrays.equals(numbers, copyArr1));
		System.out.println("CopyArr1 == copyArr2: " + Arrays.equals(copyArr1, copyArr2));
		
		
		Arrays.sort(numbers);
		System.out.println("Sorted: " + Arrays.toString(numbers));
		
		System.out.println("Has 90?: " + Arrays.binarySearch(numbers, 90));
		System.out.println("Has 300?: " + Arrays.binarySearch(numbers, 300));
	
		Arrays.fill(copyArr1, 7);
		
		System.out.println("After filling: " + Arrays.toString(copyArr1));
	}

}
