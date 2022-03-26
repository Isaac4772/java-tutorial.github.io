package CollectionAssignments.Assignment2;

import java.time.LocalDate;
import java.util.Scanner;

import static CollectionAssignments.Assignment2.Database.*;

public class Testing {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            boolean stop = false;
            while (!stop) {
                System.out.println("\nWhat would you like to do?");
                System.out.println("1. View Books");
                System.out.println("2. View Categories");
                System.out.println("3. View Authors");
                System.out.println("4. Add a new Book");
                switch (Integer.parseInt(input.nextLine())) {
                    case 1 -> {
                        {
                            System.out.println("1. View All");
                            System.out.println("2. View By Category");
                            System.out.println("3. View By Author");
                            switch (Integer.parseInt((input.nextLine()))) {
                                case 1 -> viewBooks();
                                case 2 -> viewByCategory(input);
                                case 3 -> viewByAuthor(input);
                                default -> System.err.println("Invalid Choice");
                            }
                        }
                    }
                    case 2 -> viewCategory();
                    case 3 -> viewAuthor();
                    case 4 -> addBook(input);
                    default -> System.err.println("Invalid Choice");
                }
                System.out.print("Do you want to stop the program now?(y/n): ");
                var flag = input.nextLine();
                if (flag.startsWith("Y") || flag.startsWith("y")) {
                    stop = true;
                }
            }
        }
    }

    private static void addBook(Scanner input) {
        System.out.print("\nTitle: ");
        var title = input.nextLine();
        System.out.print("Author name: ");
        var authorName = input.nextLine();
        var authorIndex = -1;
        for (var i = 0; i < authorList.size(); i++) {
            if (authorList.get(i).getName().equalsIgnoreCase(authorName)) {
                authorIndex = i;
                break;
            }
        }

        if (authorIndex == -1) {
            System.out.print("This is a new author.\nEnter author's country: ");
            var authorCountry = input.nextLine();
            authorList.add(new Author(authorName, authorCountry));
            authorIndex = authorList.size() - 1;
        }


        System.out.println("Choose Category: \n(Enter 0(zero) if you would like to add a new category)");
        for (var i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i));
        }
        var index = 0;
        try {
            index = Integer.parseInt(input.nextLine());
        } catch (Exception e) {
            System.err.println("Invalid choice");
        }

        var category = "";
        if (index == 0) {
            System.out.print("Enter a new category: ");
            categoryList.add(input.nextLine());
            category = categoryList.get(categoryList.size() - 1);
        } else
            category = categoryList.get(index - 1);


        System.out.print("Published Date: ");
        var publishedDate = LocalDate.parse(input.nextLine());

        Book newBook = new Book(bookList.size(), title, publishedDate, category, authorList.get(authorIndex));
        bookList.add(newBook);

        System.out.println("New Book has been added!");
    }

    private static void viewCategory() {
        System.out.println("\n_____Category List_____");
        categoryList.forEach(System.out::println);
    }

    private static void viewBooks() {
        System.out.println("\n_____Book List_____");
        bookList.forEach(s -> System.out.println(s.getTitle()));
    }

    private static void viewByCategory(Scanner input) {
        System.out.println("Choose category: ");
        for (var i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i));
        }
        var index = -1;
        try {
            index = Integer.parseInt(input.nextLine());
        } catch (Exception e) {
            System.err.println("Invalid choice");
        }
        var category = categoryList.get(index - 1);

        bookList.stream().filter(s -> s.getCategory().equalsIgnoreCase(category)).forEach(s -> System.out.println(s.getTitle()));
    }

    private static void viewByAuthor(Scanner input) {
        System.out.println("Choose author: ");
        for (var i = 0; i < authorList.size(); i++) {
            System.out.println((i + 1) + ". " + authorList.get(i).getName());
        }
        var index = -1;
        try {
            index = Integer.parseInt(input.nextLine());
        } catch (Exception e) {
            System.err.println("Invalid choice");
        }
        var author = authorList.get(index - 1);

        bookList.stream().filter(s -> s.getAuthor().getName().equalsIgnoreCase(author.getName())).forEach(s -> System.out.println(s.getTitle()));
    }

    private static void viewAuthor() {
        System.out.println("\n_____Author List_____");
        authorList.forEach(s -> System.out.println(s.getName()));
    }

}
