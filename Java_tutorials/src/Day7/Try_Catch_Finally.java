package Day7;

public class Try_Catch_Finally {
	public static void main(String[] args) {
		try {
			String name = null;
			System.out.println("Length: " + name.length());
		} catch (Exception e) {
			System.err.println("It does not allocate!");
		}finally {
			System.out.println("Finally Block");
		}
		System.out.println("Final Statement!");
	}
}
