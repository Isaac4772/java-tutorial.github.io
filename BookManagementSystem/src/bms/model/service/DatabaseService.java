package bms.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bms.model.entity.Author;
import bms.model.entity.Book;
import bms.model.entity.Category;

public class DatabaseService {

	public static List<Author> getAllAuthors() {
		List<Author> list = new ArrayList<>();
		try (Connection con = MyConnection.getConnection()) {

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
		try (Connection con = MyConnection.getConnection()) {

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
		List<Book> list = new ArrayList<>();
		try (Connection con = MyConnection.getConnection()) {
			String query = """
					SELECT b.*,a.name 'author_name',c.name 'category_name'
					FROM books b, authors a, categories c
					WHERE b.author_id = a.author_id AND
					b.category_id = c.category_id
					""";
			PreparedStatement pstm = con.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Book book = new Book();

				book.setCode(rs.getInt("code"));
				book.setTitle(rs.getString("title"));
				book.setPublish_date(LocalDate.parse(rs.getString("published_date")));

				Category cat = new Category();
				cat.setId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				book.setCategory(cat);

				Author auth = new Author();
				auth.setId(rs.getInt("author_id"));
				auth.setName(rs.getString("author_name"));

				book.setAuthor(auth);

				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean saveBook(Book book) {
		boolean result = false;
		try (Connection con = MyConnection.getConnection()) {
			String query = "INSERT INTO books(code,title,published_date,author_id,category_id)VALUES(?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, book.getCode());
			pstm.setString(2, book.getTitle());
			pstm.setDate(3, Date.valueOf(book.getPublish_date()));
			pstm.setInt(4, book.getAuthor().getId());
			pstm.setInt(5, book.getCategory().getId());

			pstm.executeUpdate();
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public static Book findBookByCode(int code) {
		Book book = null;
		try (Connection con = MyConnection.getConnection()) {
			String query = """
					SELECT b.*, c.name 'cat_name',a.name 'author_name' FROM books b,categories c, authors a
					WHERE code = ? AND b.category_id = c.category_id AND b.author_id = a.author_id
					""";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, code);
			// insert
			// select
			// virtual table from db is converted to resultset for select query
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				book = new Book();
				book.setTitle(rs.getString("title"));
				book.setPublish_date(LocalDate.parse(rs.getString("published_date")));

				Author auth = new Author();
				auth.setId(rs.getInt("author_id"));
				auth.setName(rs.getString("author_name"));

				book.setAuthor(auth);

				Category cat = new Category();
				cat.setId(rs.getInt("category_id"));
				cat.setName(rs.getString("cat_name"));

				book.setCategory(cat);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public static boolean updateBook(Book book) {
		boolean result = false;
		try (Connection con = MyConnection.getConnection()) {
			String query = "UPDATE books SET title = ?,published_date = ?,author_id = ?,category_id = ? WHERE code = ?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, book.getTitle());
			pstm.setDate(2, Date.valueOf(book.getPublish_date()));
			pstm.setInt(3, book.getAuthor().getId());
			pstm.setInt(4, book.getCategory().getId());
			pstm.setInt(5, book.getCode());

			pstm.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<Book> findAllBooks(LocalDate date, String author, String category) {
		List<Book> list = new ArrayList<>();
		try (Connection con = MyConnection.getConnection()) {
			String query = """
					SELECT b.*,a.name 'author_name',c.name 'category_name'
					FROM books b, authors a, categories c
					WHERE b.author_id = a.author_id AND
					b.category_id = c.category_id
					""";
			List<Object> params = new ArrayList<>();
			if (date != null) {
				query += " AND b.publish_date = ?";
				params.add(date);
			}
			if (!author.isEmpty()) {
				query += " AND a.name LIKE ?";
				params.add("%" + author + "%");
			}
			if (!category.isEmpty()) {
				query += " AND c.name = ?";
				params.add(category);
			}

			PreparedStatement pstm = con.prepareStatement(query);

			for (var i = 0; i < params.size(); i++) {
				pstm.setObject(i + 1, params.get(i));
			}

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Book book = new Book();

				book.setCode(rs.getInt("code"));
				book.setTitle(rs.getString("title"));
				book.setPublish_date(LocalDate.parse(rs.getString("published_date")));

				Category cat = new Category();
				cat.setId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				book.setCategory(cat);

				Author auth = new Author();
				auth.setId(rs.getInt("author_id"));
				auth.setName(rs.getString("author_name"));

				book.setAuthor(auth);

				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean deleteBookByCode(int code) {
		boolean result = false;
		try(Connection con = MyConnection.getConnection()) {
			String query = "DELETE FROM books WHERE code = ?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, code);
			
			pstm.executeUpdate();
			result = true;
		}
		catch (Exception e) {
			
		}
		return result;
	}

	public static void updateAuthor(Author author) {
		
		try(Connection con = MyConnection.getConnection()) {
			String query = "UPDATE authors SET name = ?, country = ? WHERE author_id = ?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, author.getName());
			pstm.setString(2, author.getCountry());
			pstm.setInt(3, author.getId());
			
			pstm.executeUpdate();
		}
		catch (Exception e) {
			
		}
		
	}

	public static void saveAuthor(String name, String country) {
		
		try (Connection con = MyConnection.getConnection()) {
			String query = "INSERT INTO authors(name,country)VALUES(?,?)";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, name);
			pstm.setString(2, country);
			
			pstm.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
