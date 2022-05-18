package bms.bms.model.service;

import bms.bms.model.entity.Author;
import bms.bms.model.entity.Book;
import bms.bms.model.entity.Category;

import java.sql.Connection;
import java.sql.Date;
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

    public static boolean saveBook(Book book) {

        return false;
    }

    public static Book findBookByCode(int code) {
        Book book = null;
        try(Connection connection = DatabaseConnection.connect()) {
            String query = """
                    SELECT b.*, a.name 'author_name', c.name 'cate_name' FROM books b, categories c, authors a
                    WHERE code=? AND b.author_id=a.author_id AND b.category_id=c.category_id""";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, code);
            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                book = new Book();
                book.setTitle(resultSet.getString("title"));
                book.setPublishedDate(LocalDate.parse(resultSet.getString("published_date")));
                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                book.setAuthor(author);
                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("cate_name"));
                book.setCategory(category);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    public static boolean updateBook(Book book) {
        boolean isUpdated = false;
        try(Connection connection = DatabaseConnection.connect()){
            String query = "UPDATE books SET title=?, published_date=?, author_id=?, category_id=? WHERE code=?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, book.getTitle());
            pstm.setDate(2, Date.valueOf(book.getPublishedDate()));
            pstm.setInt(3, book.getAuthor().getId());
            pstm.setInt(4, book.getCategory().getId());
            pstm.setInt(5, book.getCode());
            pstm.executeUpdate();
            isUpdated = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return isUpdated;
    }

    public static List<Book> FindAllBooks(String title, String author, String cate) {
        List<Book> bookList = new ArrayList<>();
        try(Connection connection = DatabaseConnection.connect()){
            String query = """
                    SELECT b.*, a.name 'author_name', c.name 'category_name' FROM
                    books b, categories c, authors a
                    WHERE b.author_id=a.author_id AND b.category_id=c.category_id
                    """;
            List<Object> params = new ArrayList<>();
            if(!title.isEmpty()){
                query += " AND b.title=?";
                params.add(title);
            }
            if(!author.isEmpty()){
                query += " AND a.name=?";
                params.add(author);
            }
            if(!cate.isEmpty()){
                query += " AND c.name=?";
                params.add(cate);
            }

            PreparedStatement pstm = connection.prepareStatement(query);
            for(var i = 0; i < params.size(); i++){
                pstm.setObject(i+1, params.get(i));
            }

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                Book book = new Book();
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));
                book.setPublishedDate(LocalDate.parse(resultSet.getString("published_date")));
                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));
                book.setCategory(category);
                Author auth = new Author();
                auth.setId(resultSet.getInt("author_id"));
                auth.setName(resultSet.getString("author_name"));
                book.setAuthor(auth);

                bookList.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return bookList;
    }
}
