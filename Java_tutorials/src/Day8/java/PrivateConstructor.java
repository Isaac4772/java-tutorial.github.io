package Day8.java;

class DatabaseConfig {

	String db_name = "employee_db";
	private static DatabaseConfig config = null;

	private DatabaseConfig() {

	}

	public static DatabaseConfig getInstance() {
		if (config == null)
			config = new DatabaseConfig();
		return config;
	}
}

public class PrivateConstructor {
	public static void main(String[] args) {
		DatabaseConfig obj1 = DatabaseConfig.getInstance();

		System.out.println("obj1's db name: " + obj1.db_name);

		DatabaseConfig obj2 = DatabaseConfig.getInstance();

		System.out.println("Obj2's db name: " + obj2.db_name);
	}
}
