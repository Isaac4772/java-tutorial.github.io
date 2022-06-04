package lms.librarymanagement.model.services;
import lms.librarymanagement.model.entity.*;;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public static List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try(Connection connection = MyConnection.getConnection()){
            String query = """
                    SELECT b.*,a.name 'author_name',c.name 'category_name'
					FROM books b, authors a, categories c
					WHERE b.author_id = a.author_id AND
					b.category_id = c.category_id
					""";
            PreparedStatement pstm = connection.prepareStatement(query);

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
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
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bookList;
    }

    public static Book getBookByCode(int code) {
        Book book = new Book();
        try(Connection connection = MyConnection.getConnection()){

            String query = """
							SELECT b.*, c.name 'cat_name',a.name 'author_name' FROM books b,categories c, authors a
							WHERE code = ? AND b.category_id = c.category_id AND b.author_id = a.author_id
																				""";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));
                book.setPublishedDate(LocalDate.parse(resultSet.getString("published_date")));
                book.setIsAvailable(resultSet.getInt("is_available"));
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("cat_name"));
                book.setCategory(category);
                Author author = new Author();
                author.setAuthor_id(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                book.setAuthor(author);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return book;
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
        try(Connection connection = MyConnection.getConnection()){
            String query = """
                    INSERT INTO `books`(`title`, `published_date`, `author_id`, `category_id`, `is_available`)
                    VALUES (?,?,?,?,?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDate(2, Date.valueOf(book.getPublishedDate()));
            preparedStatement.setInt(3, book.getAuthor().getAuthor_id());
            preparedStatement.setInt(4, book.getCategory().getCategoryId());
            preparedStatement.setInt(5, book.getIsAvailable());

            ResultSet resultSet = preparedStatement.executeQuery();
            added = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return added;
    }
}


