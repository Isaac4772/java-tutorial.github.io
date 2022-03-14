package Day10.abstraction;

public abstract class DatabaseUtil {
	private String db_name = "shop_db";

	// concrete method
	public void connectDatabase() {
		System.out.println("Connecting to " + db_name + "....");
	}

	// abstract method
	public abstract void insert();

	public abstract void update();

	public abstract boolean delete(int id);

	public abstract Object[] getAll();

	public abstract Object findById(int id);
}
