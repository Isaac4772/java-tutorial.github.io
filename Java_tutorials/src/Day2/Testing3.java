package Day2;

public class Testing3 {
	int num1 = 10;
	static int num2 = 20;
	
	static void staticMethod() {
		System.out.println("Inside static method....");
		System.out.println("num2 = " + num2);
		
	}
	
	void instanceMethod() {
		System.out.println("Inside instance method....");
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);
	}
	
	public static void main(String[] args) {
		staticMethod();
		Testing3 obj = new Testing3();
		obj.instanceMethod();
	}
}
