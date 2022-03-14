package Day8.java;

import java.time.LocalDate;

public class Copy_Constructor {
	public static void main(String[] args) {
		LocalDate publishDate = LocalDate.of(1961, 10, 16);

		Book book1 = new Book("Detective U San Shar", publishDate, 6000);

		System.out.println(book1);

		Book book2 = new Book(book1);
		System.out.println(book2);

		book2.price = 7000;
		System.out.println(book2);
	}
}

class Book {
	String title;
	LocalDate publishDate;
	int price;

	public Book(String title, LocalDate publishDate, int price) {
		super();
		this.title = title;
		this.publishDate = publishDate;
		this.price = price;
	}

	public Book(Book other) {
		this.title = other.title;
		this.price = other.price;
		this.publishDate = other.publishDate;
	}

	@Override
	public String toString() {
		String str = "[" + title + ", " + price + "," + publishDate + "]";
		return str;
	}
}
