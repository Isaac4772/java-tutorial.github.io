package Day10.Polymorphism;

public class Demo {
	public static void main(String[] args) {
		Developer developer;

		developer = new FrontendDeveloper();
		developer.work();

		developer = new BackendDeveloper();
		developer.work();
	}
}
