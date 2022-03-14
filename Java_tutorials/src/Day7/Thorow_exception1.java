package Day7;

public class Thorow_exception1 {

	static void checkMark(int mark) throws ArithmeticException {
		if (mark < 0 || mark > 100) {
			ArithmeticException ex = new ArithmeticException("Invalid mark!");
			throw ex;
		}
	}

	public static void main(String[] args) {
		try {
			checkMark(-2);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
}
