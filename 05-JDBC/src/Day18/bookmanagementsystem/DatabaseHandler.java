package Day18.bookmanagementsystem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    public static List<Author> retrieveAuthors(){
        List<Author> authorList = new ArrayList<>();

        try(Connection connection = MyConnection.createConnection()) {
            String query = "SELECT * FROM authors";
            PreparedStatement pstm = connection.prepareStatement(query);

            ResultSet resultSet = pstm.executeQuery();

            while(resultSet.next()){
                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("name"));
                author.setCountry(resultSet.getString("country"));

                authorList.add(author);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return authorList;
    }

    public static List<Category> retrieveCategory(){
        List<Category> categoryList = new ArrayList<>();
        try(Connection connection = MyConnection.createConnection()){
            String query = "SELECT * FROM categories";
            PreparedStatement pstm = connection.prepareStatement(query);

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));

                categoryList.add(category);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    public static List<Book> findAllBooks(){
        List<Book> bookList = new ArrayList<>();
        try(Connection connection = MyConnection.createConnection()){

            String query = """
                SELECT b.*, a.name 'author_name', a.country, c.name 'category_name'
                FROM books b, authors a, categories c
                WHERE b.author_id = a.author_id AND b.category_id = c.category_id
                            """;
            PreparedStatement pstm = connection.prepareStatement(query);

            ResultSet resultSet = pstm.executeQuery();

            while(resultSet.next()){
                Book book = new Book();
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));
                book.setPublished_date(LocalDate.parse(resultSet.getString("published_date")));

                Author author = new Author();
                author.setCountry(resultSet.getString("country"));
                author.setName(resultSet.getString("author_name"));
                author.setId(resultSet.getInt("author_id"));

                book.setAuthor(author);

                Category category = new Category();
                category.setName(resultSet.getString("category_name"));
                category.setId(resultSet.getInt("category_id"));
                book.setCategory(category);

                bookList.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bookList;
    }

    public static Author findAuthorByName(String name) {
        Author author = null;
        try(Connection connection = MyConnection.createConnection()){
            String query = "SELECT * FROM authors WHERE name=?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, name);
            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return author;
    }

    public static List<Book> FindBookByAuthor(int id) {
        List<Book> books = new ArrayList<>();
        try(Connection connection = MyConnection.createConnection()){
            String query = "SELECT b.*, c.name FROM books b, categories c WHERE author_id=? AND b.category_id=c.category_id";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                Book book = new Book();
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));
                book.setPublished_date(LocalDate.parse(resultSet.getString("published_date")));

                Category category = new Category();
                category.setName(resultSet.getString("name"));

                book.setCategory(category);
                books.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    public static Category findCategoryByName(String name) {
        Category category = null;

        try (Connection connection = MyConnection.createConnection()) {
            String query = "SELECT * FROM categories WHERE name=?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, name);
            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }

    public static List<Book> FindBookByCategory(int id) {
        List<Book> books = new ArrayList<>();
        try(Connection connection = MyConnection.createConnection()){
            String query = "SELECT b.*, a.name FROM books b, authors a WHERE category_id=? AND b.author_id=a.author_id";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                Book book = new Book();
                book.setCode(resultSet.getInt("code"));
                book.setTitle(resultSet.getString("title"));

                Author author = new Author();
                author.setName(resultSet.getString("name"));
                book.setAuthor(author);

                book.setPublished_date(LocalDate.parse(resultSet.getString("published_date")));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Author checkAuthorName(String name) {
        Author author = new Author();
        try(Connection connection = MyConnection.createConnection()){
            String query = "SELECT * FROM authors WHERE name=?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, name);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("name"));
//                author.setCountry(resultSet.getString("country"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return author;
    }

    public static Author addNewAuthor(Author author) {
        Author insertedAuthor = new Author();
        try (Connection con = MyConnection.createConnection()){
            String query = "INSERT INTO authors(name,country) VALUES(?,?)";
            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, author.getName());
            pstm.setString(2, author.getCountry());
            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            insertedAuthor.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertedAuthor;
    }


    public static Category verifyCategory(String cateName){
        Category cate = new Category();
        cate.setName(cateName);

        try(Connection connection = MyConnection.createConnection()){
            String query = "SELECT * FROM categories WHERE name = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, cateName);
            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                cate.setId(rs.getInt("category_id"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cate;
    }
//    public static Category checkCategoryName(String nextLine) {
//        Category cate = new Category();
//        try (Connection con = MyConnection.createConnection()){
//            String query = "SELECT * FROM categories WHERE name = ?";
//            PreparedStatement pstm = con.prepareStatement(query);
//            pstm.setString(1, nextLine);
//            ResultSet rs = pstm.executeQuery();
//
//            if(rs.next()) {
//                cate.setId(rs.getInt("category_id"));
//                cate.setName(rs.getString("name"));
//            }
//            else {
//                System.out.println("This Categories is new .");
//                cate = addNewCategory(nextLine);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cate;
//    }

//    public static Category addNewCategory(String category_name) {
//        Category category = new Category();
//
//        try(Connection connection = MyConnection.createConnection()){
//            String query = "SELECT * FROM categories WHERE name=?";
//            PreparedStatement pstm = connection.prepareStatement(query);
//            pstm.setString(1, category_name);
//            ResultSet resultSet = pstm.executeQuery();
//
//            if(resultSet.next()){ // category already exists
//                category.setId(resultSet.getInt("category_id"));
//            }
//            else{
//                String insert = "INSERT INTO categories(name) VALUES(?)";
//                pstm = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
//                pstm.setString(1, category_name);
//                pstm.executeQuery();
//
//                resultSet = pstm.getGeneratedKeys();
//                resultSet.next();
//                category.setId(resultSet.getInt(1));
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return category;
//    }

    public static boolean addBook(Book book) throws SQLException {
        boolean result;
        Connection con = null;
        try {
            con = MyConnection.createConnection();
            con.setAutoCommit(false);

            //author
            Author author = book.getAuthor();
            if(author.getId() == 0){
            String query = "INSERT INTO authors(name,country) VALUES(?,?)";
            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, author.getName());
            pstm.setString(2, author.getCountry());
            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            author.setId(id);
            book.setAuthor(author);
            }

            //category
            Category cate = book.getCategory();
            if(cate.getId() == 0){
                String insert = "INSERT INTO categories(name) VALUES(?)";
                PreparedStatement pstm;
                pstm = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                pstm.setString(1, cate.getName());
                pstm.executeUpdate();


                ResultSet resultSet = pstm.getGeneratedKeys();
                resultSet.next();
                cate.setId(resultSet.getInt(1));
                book.setCategory(cate);
            }

            //book
            String query = """
					INSERT INTO books(title,published_date,author_id,category_id)VALUES(?,?,?,?)
					""";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, book.getTitle());
            pstm.setDate(2, Date.valueOf(book.getPublished_date()));
            pstm.setInt(3, book.getAuthor().getId());
            pstm.setInt(4, book.getCategory().getId());

            pstm.executeUpdate();
            result = true;

            //commit
            con.commit();

        }catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            result = false;
        }
        finally{
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
