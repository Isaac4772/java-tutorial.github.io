package Day8.java;

public class StaticData {

	public static void main(String[] args) {

		try {
			Employee emp1 = new Employee();
			emp1.initData("Jeon", 900000);
			Employee emp2 = new Employee();
			emp2.initData("Isaac", 18000000);
			Employee emp3 = new Employee();
			emp3.initData("John", 1200000);

			Employee.changeNoOfEmployee(10);
			Employee emp4 = new Employee();
			emp4.initData("Honey", 100000);
			System.out.println("__Emp1 Data__");
			emp1.showData();
			System.out.println("__Emp2 Data__");
			emp2.showData();
			System.out.println("__Emp3 Data__");
			emp3.showData();
			System.out.println("__Emp4 Data__");
			emp4.showData();
			
			emp4.viewInformation();
		} catch (AppException e) {
			System.err.println(e.getMessage());
		}

	}
}

class AppException extends Exception {

	private static final long serialVersionUID = 1L;

	public AppException(String msg) {
		super(msg);
	}
}

class Employee {
	static int noOfEmployee = 3;
	static int nextId = 1;
	final int empId;
	String name;
	int salary;

	public Employee() throws AppException {
		if (nextId > noOfEmployee)
			throw new AppException("Sorry, limited number has been reached.");
		this.empId = nextId;
		nextId++;
	}

	public void initData(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public void changeData(String editName, int editSal) {
		if (!editName.equalsIgnoreCase(this.name))
			this.name = editName;
		if (editSal != this.salary)
			this.salary = editSal;
	}

	public void showData() {
		System.out.println(this.empId + "\t" + this.name + "\t" + this.salary);
	}

	public static void changeNoOfEmployee(int count) {
		noOfEmployee = count;
	}

	public void viewInformation() {
		System.out.println("Total emp: " + noOfEmployee);
		System.out.println("Current Employee Information");
		showData();
		System.out.println("Next emp id: " + nextId);
	}
}