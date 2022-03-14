package Assignmets;

public class Assignment9 {

	public static void main(String[] args) {

		String[] numbers = { "5", "10", "15", "20", "25" };
		ArrayOfNumbers arr = null;
		try {
			arr = new ArrayOfNumbers(numbers, numbers.length);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Over Limit!");
		} catch (IllegalArgumentException e) {
			System.err.println("Not a number");
		} catch (ArithmeticException e) {
			System.err.println(e.getMessage());
		}
		arr.showInfo();

	}

}

class ArrayOfNumbers {
	private int len;
	private int[] numbers;
	private int total;

	public ArrayOfNumbers(String[] numbers, int len) {
		this.numbers = new int[len];
		this.len = len;
		for (int i = 0; i < numbers.length; i++) {
			this.numbers[i] = Integer.parseInt(numbers[i]);
			this.total += this.numbers[i];
		}
	}

	public int getTotal() {
		return total;
	}

	public int getAverage() {
		return total / len;
	}

	public int getMin() {
		int min = numbers[0];
		for (int num : numbers) {
			if (num < min)
				min = num;
		}
		return min;
	}

	public int getMax() {
		int max = numbers[0];
		for (int num : numbers) {
			if (num > max)
				max = num;
		}
		return max;
	}

	public void showInfo() {
		System.out.println("Total: " + total);
		System.out.println("Min: " + getMin());
		System.out.println("Max: " + getMax());

	}

}
