package Day11;

public class Demo {

	public static void main(String[] args) {
		Author auth = new Author("Mya Than Tint", "Myain");
		Book book = new Book("War and Peace", 6000, auth);

		System.out.println("Book Name; " + book.name);
		System.out.println("Book Price: " + book.price);
		System.out.println("___Author Info___");
		System.out.println("Author Name: " + book.author.name);
		System.out.println("Native Town: " + book.author.nativeTown);
	}
}
