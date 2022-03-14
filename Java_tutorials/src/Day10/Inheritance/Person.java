package Day10.Inheritance;

public class Person {
	private String name;

	public Person() {

	}

	public Person(String name) {
		this.name = name;
	}

	public void showInfo() {
		System.out.println("Name: " + name);
	}
}
