package Day8.java;

public class Block_Test {

	static final int MIN_LENGTH;
	String phone;

	{
		System.out.println("This is instance block");
		phone = "Invalid number";
	}

	static {
		System.out.println("This is static block");
		MIN_LENGTH = 9;
	}

	public Block_Test(String phone) {
		System.out.println("This is a constructor block");
		if (phone.length() >= MIN_LENGTH)
			this.phone = phone;
	}

	public static void main(String[] args) {
		Block_Test test = new Block_Test("09774511562");

		System.out.println(test.phone);
	}

}
