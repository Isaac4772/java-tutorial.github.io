package Day2;

public class Testing {

	static String name; // static variable
	int price;

	void display() {
		int barCode = 1001; // instance variable
		name = "Juice";
		price = 1500;

		System.out.println("Code: " + barCode);
		System.out.println("Name: " + name);
		System.out.println("Price: " + price);
	}

	public static void main(String[] args) {
		Testing obj = new Testing();
		obj.display();
	}

}
