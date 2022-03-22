package Day12.lambda;

public class Testing {
	public static void main(String[] args) {
//		MyClass obj = new MyClass();
//		obj.display("Aung Paing");

//		MyInterface1 obj = new MyInterface1() {
//
//			@Override
//			public void display(String input) {
//				System.out.println(input);
//			}
//		};
//
//		obj.display("Aung Aung");

		MyInterface1 obj = (str) -> System.out.println(str);
		obj.display("Aung Paing");

		MyInterface3 obj2 = (email, password) -> {
			if (email.equals("jk@gmail.com") && password.equals("123"))
				return true;
			else
				return false;
		};

		System.out.println(obj2.checkLogin("admin@gmail.com", "123") ? "Login success" : "Login faileed");
		System.out.println(obj2.checkLogin("jk@gmail.com", "123") ? "Login success" : "Login failed");

		MyInterface2 sum = (n1, n2) -> n1 + n2;
		MyInterface2 sub = (n1, n2) -> n1 - n2;
		MyInterface2 mul = (n1, n2) -> n1 * n2;

		System.out.println(sum.operate(100, 200));
		System.out.println(sub.operate(200, 100));
		System.out.println(mul.operate(3, 2));
	}
}

//class MyClass implements MyInterface1 {
//
//	@Override
//	public void display(String input) {
//		System.out.println(input);
//	}
//
//}
