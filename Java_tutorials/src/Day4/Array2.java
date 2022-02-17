package Day4;

import java.util.Arrays;

public class Array2 {
	
	static void changeValue(int[] input) {
		input[0] = 500;
	}

	public static void main(String[] args) {
		
		int[] arr = {100, 200, 300, 400};
		
		//total
		int total = Arrays.stream(arr).sum();
		System.out.println("Total: " + total);
		
		System.out.println("Min: " + Arrays.stream(arr).min().getAsInt());
		System.out.println("Max: " + Arrays.stream(arr).max().getAsInt());
		
		changeValue(arr);
		
		System.out.println("After calling method: " + Arrays.toString(arr));
	}

}
