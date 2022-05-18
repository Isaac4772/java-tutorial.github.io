package bms.model.service;

import bms.model.entity.Author;
import bms.model.entity.Book;
import bms.model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatabaseService {
    public static List<Author> getAllAuthors() {
        List<Author> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.connect()) {

            String query = "SELECT * FROM authors";
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Author obj = new Author();
                obj.setId(rs.getInt("author_id"));
                obj.setName(rs.getString("name"));
                obj.setCountry(rs.getString("country"));

                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.connect()) {

            String query = "SELECT * FROM categories ORDER BY category_id";
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Category obj = new Category();
                obj.setId(rs.getInt("category_id"));
                obj.setName(rs.getString("name"));

                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()){
            String query = "SELECT b.*, a.name 'author_name', c.name 'category_name' FROM books b, categories c, authors a WHERE b.author_id=a.author_id AND b.category_id=c.category_id";
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()){
                Book book = new Book();
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));
                book.setPublishedDate(LocalDate.parse(resultSet.getString("published_date")));
                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));
                book.setCategory(category);
                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                book.setAuthor(author);

                bookList.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return bookList;
    }
}
