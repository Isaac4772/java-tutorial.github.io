package Day12.lambda;

@FunctionalInterface
public interface MyInterface1 {

	void display(String input);

}

@FunctionalInterface
interface MyInterface2 {

	int operate(int a, int b);
}

@FunctionalInterface
interface MyInterface3 {
	boolean checkLogin(String email, String password);
}
