package CollectionAssignments.Assignment2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static List<String> categoryList = new ArrayList<String>(
            List.of("Drama", "Novel", "Biography", "Sci-fi")
    );
    static List<Author> authorList = new ArrayList<Author>(
            List.of(new Author("Charles Dickens", "England"),
                    new Author("Alessandro Manzoni", "Italy"),
                    new Author("Pascal Khoo Thwe", "Myanmar")
            ));
    static List<Book> bookList = new ArrayList<Book>(
            List.of(new Book(0, "A Christmas Carol", LocalDate.parse("1843-12-19"), categoryList.get(0), authorList.get(0)),
                    new Book(1, "The Betrothed", LocalDate.parse("1827-06-15"), categoryList.get(1), authorList.get(1)),
                    new Book(2, "From The Land Of Green Ghosts", LocalDate.parse("2003-12-02"), categoryList.get(2), authorList.get(2)
                    )
            ));
}
