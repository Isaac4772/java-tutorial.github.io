package Day10.Inheritance;

public class Demo {
	public static void main(String[] args) {
		Person person = new Person("James");
		person.showInfo();

		Teacher teacher = new Teacher("Aung Aung", "Instructor");
		teacher.showInfo();

		Person person1 = new Teacher("Aung Paing", "Software Developer");
		person1.showInfo();

	}
}
