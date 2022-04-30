package Day18.bookmanagementsystem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) throws SQLException {
        String input = """
                1. View Authors
                2. View Books
                3. View Categories
                4. Add Book
                Choose 1, 2, 3 or 4:
                """;
        System.out.println(input);

        Scanner sc = new Scanner(System.in);
        int menu = Integer.parseInt(sc.nextLine());

        switch (menu) {
            case 1 -> viewAuthors();
            case 2 -> viewBooks();
            case 3 -> viewCategories();
            case 4 -> addBook();
            default -> System.err.println("Invalid Number!");
        }
    }

    private static void addBook() throws SQLException{
        Book book = new Book();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book information..... ");

        // System.out.println("Enter Code :");
        // book.setCode(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Title");
        book.setTitle(sc.nextLine());
        System.out.print("Enter published date(yyyy-mm-dd): ");
        book.setPublished_date(LocalDate.parse(sc.nextLine()));

        System.out.print("Author Name : ");
        String authorName = sc.nextLine();
        Author author = DatabaseHandler.checkAuthorName(authorName);
        if(author.getId() == 0){
            System.out.println("This is new author");
            System.out.print("Enter country name: ");
            author.setCountry(sc.nextLine());
//            author.setName(authorName);
        }
        book.setAuthor(author);


        System.out.print("Category :");
        String cateName = sc.nextLine();
        Category cate = DatabaseHandler.verifyCategory(cateName);
        book.setCategory(cate);

        boolean result = DatabaseHandler.addBook(book);
        if(result)
            System.out.println("A new book is inserted");
        else
            System.out.println("Something went wrong, please try again");
        sc.close();
    }

    private static void viewCategories() {
        List<Category> categoryList = DatabaseHandler.retrieveCategory();
        System.out.println("Id\tDescription");
        categoryList.forEach(s -> System.out.println(s.getId() + "\t" + s.getName()));
    }

    private static void viewBooks() {
        Scanner sc = new Scanner(System.in);
        String input = """
                1. View All
                2. View By Category
                3. View By Author
                Choose 1, 2 or 3:
                """;
        System.out.println(input);
        int menu = Integer.parseInt(sc.nextLine());

        switch (menu) {
            case 1 -> viewAllBooks();
            case 2 -> viewBooksByCategory();
            case 3 -> viewBooksByAuthor();
            default -> System.err.println("Invalid Number!");
        }

        sc.close();
    }

    private static void viewAllBooks() {
        List<Book> bookList = DatabaseHandler.findAllBooks();
        System.out.println("Code\tTitle\tPublished Date\tCategory\tAuthor");

        bookList.forEach(book -> {
            System.out.print(book.getCode() + "\t");
            System.out.print(book.getTitle() + "\t");
            System.out.print(book.getPublished_date() + "\t");
            System.out.print(book.getCategory().getName() + "\t");
            System.out.println(book.getAuthor().getName() + "\t");
        });
    }

    private static void viewBooksByCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter category name: ");
        String name = sc.nextLine();

        Category category = DatabaseHandler.findCategoryByName(name);

        if (category == null)
            System.err.println("Sorry " + name + " could not be found :[");
        else {
            List<Book> bookList = DatabaseHandler.FindBookByCategory(category.getId());

            System.out.println("Code\tBook Title\tAuthor\tPublished Date");
            bookList.forEach(book -> {
                System.out.print(book.getCode() + "\t");
                System.out.print(book.getTitle() + "\t");
                System.out.print(book.getAuthor().getName() + "\t");
                System.out.println(book.getPublished_date());
            });
        }
        sc.close();
    }

    private static void viewBooksByAuthor() {
        String name;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter author's name: ");
        name = sc.nextLine();

        Author author = DatabaseHandler.findAuthorByName(name);

        if (author == null)
            System.err.println("Sorry " + name + " could not be found :[");
        else { // author exits
            List<Book> bookList = DatabaseHandler.FindBookByAuthor(author.getId());

            // has author but no book
            if (bookList.size() == 0)
                System.out.println("This author has no book");
            // has author and his books
            else {
                System.out.println("Code\tBook Title\tCategory\tPublished Date");
                bookList.forEach(book -> {
                    System.out.print(book.getCode() + "\t");
                    System.out.print(book.getTitle() + "\t");
                    System.out.print(book.getCategory().getName() + "\t");
                    System.out.println(book.getPublished_date());
                });
            }
        }
        sc.close();
    }

    private static void viewAuthors() {
        List<Author> authorList = DatabaseHandler.retrieveAuthors();
        System.out.println("Id\tAuthor Name\tCountry");
        System.out.println("-----------");
        authorList.forEach(s -> System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getCountry()));
    }
}
