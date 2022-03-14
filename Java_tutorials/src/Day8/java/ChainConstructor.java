package Day8.java;

public class ChainConstructor {

	public ChainConstructor() {
		
		this(100,200);
		System.out.println("This is default constructor");
	}
	
	public ChainConstructor(int x) {
		System.out.println("One argument constructor");
	}
	
	public ChainConstructor(int x, int y) {
		this(x);
		System.out.println("This is two argument constructor");
	}

class Employee{
	private String name;
	public Employee() {
		
	}
	
	public Employee(String name){
		this.name = name;
	}
}

class Teacher extends Employee{
	private String position;
	
	public Teacher(String name, String pos) {
		super(name);
		this.position = pos;
	}
}

public static void main(String[] args) {
	// TODO Auto-generated method stub
	
}

}
