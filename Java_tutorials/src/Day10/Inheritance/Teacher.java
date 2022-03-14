package Day10.Inheritance;

public class Teacher extends Person {

	private String position;

	public Teacher(String name, String position) {
		super(name);
		this.position = position;
	}

	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		super.showInfo();
		System.out.println("Position: " + this.position);
		System.out.println("_______________");
	}

}
