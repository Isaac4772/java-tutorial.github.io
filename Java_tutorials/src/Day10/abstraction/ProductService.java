package Day10.abstraction;

public class ProductService extends DatabaseUtil {

	@Override
	public void insert() {
		System.out.println("Insert into product");
	}

	@Override
	public void update() {
		System.out.println("Update product");
	}

	@Override
	public boolean delete(int id) {
		System.out.println("Delete product " + id);
		return false;
	}

	@Override
	public Object[] getAll() {
		System.out.println("Select all products");
		return null;
	}

	@Override
	public Object findById(int id) {
		System.out.println("Select product " + id);
		return null;
	}

}
