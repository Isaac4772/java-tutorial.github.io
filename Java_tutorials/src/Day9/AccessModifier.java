package Day9;

public class AccessModifier {

}

class A{
	private int a;
	
	void test() {
		a = 100;
	}
}

//class B{
//	
//	void test() {
//		A obj = new A();
//		obj.a = 1;
//	}
//}