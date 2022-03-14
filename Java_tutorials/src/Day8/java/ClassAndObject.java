package Day8.java;

class Phone {

//	fields
	String brand;
	String color;
	int price;

//	constructor
	public Phone() {

	}

	public Phone(String brand, String color, int price) {
		super();
		this.brand = brand;
		this.color = color;
		this.price = price;
	}

//	method
	public void call() {
		System.out.println("Phone calling can be made here!");
	}

	public void sendSms() {
		System.out.println("Message can be sent here!");
	}

	@Override
	public String toString() {
		return "Phone [brand=" + brand + ", color=" + color + ", price=" + price + "]";
	}

}

public class ClassAndObject {
	public static void main(String[] args) {
		Phone phone1 = new Phone();
		phone1.brand = "Samsung";
		phone1.color = "Black";
		phone1.price = 1000000;

		phone1.sendSms();

		Phone phone2 = new Phone("Iphone", "White", 25000000);

		phone2.call();
		
		System.out.println(phone1);
		System.out.println(phone2);
	}
}
