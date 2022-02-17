package Day4;

public class Array4 {

	public static void main(String[] args) {
		String[] names = { "Jeon", "Yuri", "Cherry" };
		int[][] marks = { { 65, 73, 86, 84, 59 }, { 71, 80, 90, 69, 95 }, { 75, 75, 99, 96, 99 } };

		int row = marks.length;
		int col = marks[0].length;

		for (var r = 0; r < row; r++) {
			var total = 0;
			for (var c = 0; c < col; c++) {
				total += marks[r][c];
			}
			System.out.println("Total marks obtained by student " + names[r] + ": " + total);
			System.out.println("Average Mark: " + (float) (total / 5));
			
		}
	}
}
