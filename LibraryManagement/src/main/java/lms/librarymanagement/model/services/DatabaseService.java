package lms.librarymanagement.model.services;

import lms.librarymanagement.model.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public static List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection()) {
            String query = """
                    SELECT b.*,a.name 'author_name',c.name 'category_name'
                    FROM books b, authors a, categories c
                    WHERE b.author_id = a.author_id AND
                    b.category_id = c.category_id
                    """;
            PreparedStatement pstm = connection.prepareStatement(query);

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));
                book.setPublishedDate(LocalDate.parse(resultSet.getString("published_date")));
                book.setIsAvailable(resultSet.getInt("is_available"));
                Category category = new Category();
                category.setName(resultSet.getString("category_name"));
                Author author = new Author();
                author.setName(resultSet.getString("author_name"));
                book.setCategory(category);
                book.setAuthor(author);
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        try (Connection con = MyConnection.getConnection()) {

            String query = "SELECT * FROM categories ORDER BY category_id";
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Category obj = new Category();
                obj.setCategoryId(rs.getInt("category_id"));
                obj.setName(rs.getString("name"));

                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Author> getAllAuthors() {
        List<Author> list = new ArrayList<>();
        try (Connection con = MyConnection.getConnection()) {

            String query = "SELECT * FROM authors";
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Author obj = new Author();
                obj.setAuthor_id(rs.getInt("author_id"));
                obj.setName(rs.getString("name"));
                obj.setCountry(rs.getString("country"));

                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateBook(Book book) {
        boolean result = false;
        try (Connection con = MyConnection.getConnection()) {
            String query = "UPDATE books SET title = ?,published_date = ?,author_id = ?,category_id = ?,is_available = ? WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, book.getTitle());
            pstm.setDate(2, Date.valueOf(book.getPublishedDate()));
            pstm.setInt(3, book.getAuthor().getAuthor_id());
            pstm.setInt(4, book.getCategory().getCategoryId());
            pstm.setInt(5, book.getIsAvailable());
            pstm.setInt(6, book.getCode());

            pstm.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean addNewBook(Book book) {
        boolean added = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query1 = """
                    INSERT INTO `books`(`title`, `published_date`, `author_id`, `category_id`, `is_available`)
                    VALUES (?,?,?,?,?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDate(2, Date.valueOf(book.getPublishedDate()));
            preparedStatement.setInt(3, book.getAuthor().getAuthor_id());
            preparedStatement.setInt(4, book.getCategory().getCategoryId());
            preparedStatement.setInt(5, book.getIsAvailable());
            preparedStatement.executeQuery();
            added = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }

    public static boolean deleteBook(int bookCode) {
        boolean deleted = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = "DELETE FROM books WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookCode);
            preparedStatement.executeQuery();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    public static List<BorrowedBook> getBorrowedBooks() {
        List<BorrowedBook> bookList = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection()) {
            String query = "SELECT bb.*, b.code, m.card_id FROM borrow_books bb, books b, members m"
                    + " WHERE bb.book_id=b.code AND bb.card_id=m.card_id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BorrowedBook borrowedBook = new BorrowedBook();
                borrowedBook.setId(resultSet.getInt("id"));
                borrowedBook.setBookId(resultSet.getInt("book_id"));
                borrowedBook.setCardId(resultSet.getInt("card_id"));
                borrowedBook.setBorrowDate(LocalDate.parse(resultSet.getString("borrow_date")));
                borrowedBook.setDueDate(LocalDate.parse(resultSet.getString("due_date")));
                if (resultSet.getString("return_date") == null)
                    borrowedBook.setReturnDate(null);
                else
                    borrowedBook.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
                borrowedBook.setFine(resultSet.getInt("fine"));
                bookList.add(borrowedBook);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection()) {
            String query = "SELECT * FROM members ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setCardId(resultSet.getInt("card_id"));
                member.setName(resultSet.getString("name"));
                member.setRollNo(resultSet.getString("roll_no"));
                member.setAcademicYear(Year.parse(resultSet.getString("academic_year")));
                member.setClassYear(resultSet.getString("class_year"));
                member.setCreatedAt(LocalDate.parse(resultSet.getString("register_date")));
                member.setExpireAt(LocalDate.parse(resultSet.getString("expire_date")));
                memberList.add(member);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberList;
    }

    public static boolean addNewBorrowedBook(BorrowedBook book) {
        boolean added = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = """
                    INSERT INTO `borrow_books`(`card_id`, `book_id`,`borrow_date` ,`due_date`)
                    VALUES (?,?,?,?)
                    """;

            String query2 = """
                    UPDATE `books` SET `is_available`= 0 WHERE code = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, book.getCardId());
            preparedStatement.setInt(2, book.getBookId());
            preparedStatement.setDate(3, Date.valueOf(book.getBorrowDate()));
            preparedStatement.setDate(4, Date.valueOf(book.getBorrowDate().plusDays(7)));
            preparedStatement.executeQuery();

            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setInt(1, book.getBookId());
            preparedStatement2.executeQuery();
            added = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }

    public static List<Book> getAvailableBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection()) {
            String query = """
                    SELECT b.*,a.name 'author_name',c.name 'category_name'
                    FROM books b, authors a, categories c
                    WHERE b.author_id = a.author_id AND
                    b.category_id = c.category_id AND is_available = 1
                    """;

            PreparedStatement pstm = connection.prepareStatement(query);

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));
                book.setPublishedDate(LocalDate.parse(resultSet.getString("published_date")));
                book.setIsAvailable(resultSet.getInt("is_available"));
                Category category = new Category();
                category.setName(resultSet.getString("category_name"));
                Author author = new Author();
                author.setName(resultSet.getString("author_name"));
                book.setCategory(category);
                book.setAuthor(author);
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static boolean returnBook(BorrowedBook borrowedBook) {
        boolean returned = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = "UPDATE `books` SET `is_available`='1' WHERE code=?";
            String query1 = "UPDATE `borrow_books` SET `return_date`=? WHERE book_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, borrowedBook.getBookId());
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            if (borrowedBook.getReturnDate() == null) {
                preparedStatement1.setDate(1, Date.valueOf(LocalDate.now()));
            }
            preparedStatement1.setInt(2, borrowedBook.getBookId());
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            returned = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returned;
    }

    public static List<BorrowedBook> getUnreturnedBooks() {
        List<BorrowedBook> unreturnedBooks = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection()) {
            String query = "SELECT bb.*, b.code, m.card_id FROM borrow_books bb, books b, members m"
                    + " WHERE bb.book_id=b.code AND bb.card_id=m.card_id AND return_date is null";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BorrowedBook borrowedBook = new BorrowedBook();
                borrowedBook.setId(resultSet.getInt("id"));
                borrowedBook.setBookId(resultSet.getInt("book_id"));
                borrowedBook.setCardId(resultSet.getInt("card_id"));
                borrowedBook.setBorrowDate(LocalDate.parse(resultSet.getString("borrow_date")));
                borrowedBook.setDueDate(LocalDate.parse(resultSet.getString("due_date")));
                borrowedBook.setFine(resultSet.getInt("fine"));
                if (resultSet.getString("return_date") == null)
                    borrowedBook.setReturnDate(null);
                else
                    borrowedBook.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
                borrowedBook.setFine(resultSet.getInt("fine"));
                unreturnedBooks.add(borrowedBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unreturnedBooks;
    }

    public static void addFine(List<BorrowedBook> booksOverDue) {
        try (Connection connection = MyConnection.getConnection()) {
            String query = "UPDATE `borrow_books` SET `fine`='500' WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            for (BorrowedBook book : booksOverDue) {
                pstm.setInt(1, book.getId());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean updateAuthor(Author author) {
        boolean updated = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = "UPDATE `authors` SET `name`=?, `country`=? WHERE author_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, author.getName());
            pstm.setString(2, author.getCountry());
            pstm.setInt(3, author.getAuthor_id());
            pstm.executeUpdate();
            updated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

    public static boolean updateCategory(Category category) {
        boolean updated = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = "UPDATE `categories` SET `name`=? WHERE category_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, category.getName());
            pstm.setInt(2, category.getCategoryId());
            pstm.executeUpdate();
            updated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;

    }

    public static boolean addNewCategory(Category category) {
        boolean added = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = "INSERT INTO `categories`(`name`) VALUES (?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, category.getName());
            pstm.executeQuery();
            added = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }

    public static boolean addNewAuthor(Author author) {
        boolean added = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = "INSERT INTO `authors`(`name`, `country`) VALUES (?,?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, author.getName());
            pstm.setString(2, author.getCountry());
            pstm.executeQuery();
            added = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }

    public static List<Librarian> getAllLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection()) {
            String query = "SELECT * FROM librarians";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("id"));
                librarian.setUsername(resultSet.getString("username"));
                librarian.setPassword(resultSet.getString("password"));
                librarian.setNrcNo(resultSet.getString("nrcno"));
                librarian.setPhone(resultSet.getString("phone"));
                librarian.setCreatedAt(LocalDate.parse(resultSet.getString("created_at")));
                librarians.add(librarian);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return librarians;
    }

    public static boolean updateLibrarian(Librarian librarian) {
        boolean updated = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = """
                    					UPDATE `librarians` SET `username`=?,`password`=?,`nrcno`=?,`phone`=?,`created_at`=? WHERE `id`=?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, librarian.getUsername());
            preparedStatement.setString(2, librarian.getPassword());
            preparedStatement.setString(3, librarian.getNrcNo());
            preparedStatement.setString(4, librarian.getPhone());
            preparedStatement.setDate(5, Date.valueOf(librarian.getCreatedAt()));
            preparedStatement.setInt(6, librarian.getId());
            preparedStatement.executeUpdate();
            updated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

    public static boolean deleteLibrarian(int id) {
        boolean deleted = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = "DELETE FROM `librarians` WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public static boolean addLibrarian(Librarian librarian) {
        boolean added = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = """
                    INSERT INTO `librarians`(`username`, `password`, `nrcno`, `phone`, `created_at`) VALUES (?,?,?,?,?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, librarian.getUsername());
            preparedStatement.setString(2, librarian.getPassword());
            preparedStatement.setString(3, librarian.getNrcNo());
            preparedStatement.setString(4, librarian.getPhone());
            preparedStatement.setDate(5, Date.valueOf(librarian.getCreatedAt()));
            preparedStatement.executeUpdate();
            added = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }

    public static boolean updateMember(Member member) {
        boolean updated = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = """
                    					UPDATE `members` SET `name`=?,`roll_no`=?,`academic_year`=?,
                    					`class_year`=?,`register_date`=?,`expire_date`=? WHERE `card_id`=?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getRollNo());
            preparedStatement.setString(3, String.valueOf(member.getAcademicYear()));
            preparedStatement.setString(4, member.getClassYear());
            preparedStatement.setDate(5, Date.valueOf(member.getCreatedAt()));
            preparedStatement.setDate(6, Date.valueOf(member.getExpireAt()));
            preparedStatement.setInt(7, member.getCardId());

            preparedStatement.executeUpdate();
            updated = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public static boolean deleteMember(int cardId) {
        boolean deleted = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query1 = "DELETE FROM borrow_books WHERE card_Id=?";
            String query = "DELETE FROM members WHERE card_id=?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement1.setInt(1, cardId);
            preparedStatement.setInt(1, cardId);
            preparedStatement1.executeUpdate();
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deleted;
    }

    public static boolean addMember(Member member) {
        boolean added = false;
        try (Connection connection = MyConnection.getConnection()) {
            String query = """
                    					INSERT INTO `members`(`name`, `roll_no`, `academic_year`, `class_year`, `register_date`, `expire_date`)
                    					VALUES (?,?,?,?,?,?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getRollNo());
            preparedStatement.setString(3, String.valueOf(member.getAcademicYear()));
            preparedStatement.setString(4, member.getClassYear());
            preparedStatement.setDate(5, Date.valueOf(member.getCreatedAt()));
            preparedStatement.setDate(6, Date.valueOf(member.getExpireAt()));
            preparedStatement.executeUpdate();
            added = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }
}
