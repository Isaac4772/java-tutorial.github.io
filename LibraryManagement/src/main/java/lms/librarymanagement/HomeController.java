package lms.librarymanagement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.*;
import lms.librarymanagement.model.services.DatabaseService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
	@FXML
	private ToggleButton btnAuthCate;

	@FXML
	private ToggleButton btnBorrowHistory;

	@FXML
	private ToggleButton btnBorrowedBooks;

	@FXML
	private ToggleButton btnHome;

	@FXML
	private ToggleButton btnLibrarians;

	@FXML
	private ToggleButton btnMembers;

	@FXML
	private ToggleButton btnSignOut;


	@FXML
	private TableColumn<Member, Year> col_academic_year_mem;

	@FXML
	private TableColumn<Member, Integer> col_card_id_mem;

	@FXML
	private TableColumn<Member, LocalDate> col_creation_date_mem;

	@FXML
	private TableColumn<Member, LocalDate> col_expire_date_mem;

	@FXML
	private TableColumn<Member, String> col_name_mem;

	@FXML
	private TableColumn<Member, String> col_roll_no_mem;

	@FXML
	private DatePicker date_creation_date_mem;

	@FXML
	private DatePicker date_expire_date_mem;

	@FXML
	private TextField txt_academic_year_mem;

	@FXML
	private TextField txt_card_id_mem;

	@FXML
	private TextField txt_class_year_mem;

	@FXML
	private TextField txt_name_mem;

	@FXML
	private TextField txt_roll_no_mem;

	@FXML
	private TextField txt_search_member;

	@FXML
	private ComboBox<String> cbo_auth;

	@FXML
	private ComboBox<String> cbo_cate;

	@FXML
	private DatePicker cbo_pub_date;

	@FXML
	private TableColumn<Book, String> col_author;

	@FXML
	private TableColumn<Author, String> col_author_country;

	@FXML
	private TableColumn<Author, Integer> col_author_id;

	@FXML
	private TableColumn<Author, String> col_author_name;

	@FXML
	private TableColumn<Book, Integer> col_avail;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_book_id;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_book_id1;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_borrowed_date;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_borrowed_date1;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_card_id;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_card_id1;

	@FXML
	private TableColumn<Book, String> col_cate;

	@FXML
	private TableColumn<Category, Integer> col_cate_id;

	@FXML
	private TableColumn<Category, String> col_cate_name;

	@FXML
	private TableColumn<Book, Integer> col_code;

	@FXML
	private TableColumn<Librarian, LocalDate> col_creation_date;

	@FXML
	private TableColumn<Member, String> col_class_year_mem;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_due_date;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_due_date1;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_fine;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_fine1;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_id;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_id1;

	@FXML
	private TableColumn<Librarian, Integer> col_id_librarian;

	@FXML
	private TableColumn<Librarian, Integer> col_nrc_no;

	@FXML
	private TableColumn<Librarian, String> col_password;

	@FXML
	private TableColumn<Librarian, String> col_phone;

	@FXML
	private TableColumn<Book, LocalDate> col_pub_date;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_return_date;

	@FXML
	private TableColumn<Book, Book> col_title;

	@FXML
	private TableColumn<Librarian, String> col_username;

	@FXML
	private DatePicker date_creation_date;

	@FXML
	private Pane pnlAuthCate;

	@FXML
	private Pane pnlBorrowHistory;

	@FXML
	private Pane pnlBorrowedBooks;

	@FXML
	private Pane pnlHome;

	@FXML
	private Pane pnlLibrarians;

	@FXML
	private Pane pnlMembers;

	@FXML
	private TableView<Author> tbl_author;

	@FXML
	private TableView<Category> tbl_cate;

	@FXML
	private TableView<Book> tbl_data;

	@FXML
	private TableView<BorrowedBook> tbl_data1;

	@FXML
	private TableView<BorrowedBook> tbl_data2;

	@FXML
	private TableView<Librarian> tbl_librarians;

	@FXML
	private TableView<Member> tbl_members;

	@FXML
	private TextField txt_author_country;

	@FXML
	private TextField txt_author_name;

	@FXML
	private TextField txt_cate_name;

	@FXML
	private TextField txt_code;

	@FXML
	private TextField txt_id;

	@FXML
	private TextField txt_nrc_no;

	@FXML
	private TextField txt_password;

	@FXML
	private TextField txt_phone;

	@FXML
	private TextField txt_search_authors;

	@FXML
	private TextField txt_search_bbook;

	@FXML
	private TextField txt_search_book;

	@FXML
	private TextField txt_search_cate;

	@FXML
	private TextField txt_search_librarian;

	@FXML
	private TextField txt_search_unreturned_books;

	@FXML
	private TextField txt_title;

	@FXML
	private TextField txt_username;

	private List<Author> authorList;
	private List<Category> categoryList;
	private static Book book;
	private static BorrowedBook borrowedBook;
	private static Author author;
	private static Category category;

	private static Member member;
	private static Librarian librarian;

	private static List<Book> bookItems;
	private static List<BorrowedBook> borrowedBooks;
	private static List<BorrowedBook> unreturnedBooks;
	private static List<Librarian> librarians;

	private static List<Member> members;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadCategories();
		loadAuthors();
		loadBooks();
		loadLibrarians();
		loadMembers();
		initializeCol(bookItems); // For Books table
		loadBorrowedBooks();
		initializeCol1(borrowedBooks); // For Borrow History table
		loadUnreturnedBooks();
		initializeCol2(unreturnedBooks); // For Unreturned (Borrowed) Book table
		checkReturnDate(); // Check if return date of borrow books are over due date and add fine
		initializeColAuthTbl(authorList);
		initializeColCateTbl(categoryList);
		initializeColLibrarians(librarians);
		initializeColMembers(members);
	}

	private void loadMembers() {
		members = DatabaseService.getAllMembers();
	}

	private void initializeColMembers(List<Member> members) {
		col_card_id_mem.setCellValueFactory(new PropertyValueFactory<>("cardId"));
		col_name_mem.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_roll_no_mem.setCellValueFactory(new PropertyValueFactory<>("rollNo"));
		col_academic_year_mem.setCellValueFactory(new PropertyValueFactory<>("academicYear"));
		col_class_year_mem.setCellValueFactory(new PropertyValueFactory<>("classYear"));
		col_creation_date_mem.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
		col_expire_date_mem.setCellValueFactory(new PropertyValueFactory<>("expireAt"));
		tbl_members.setItems(FXCollections.observableArrayList(members));

	}

	private void loadLibrarians() {
		librarians = DatabaseService.getAllLibrarians();
	}

	private void checkReturnDate() {
		List<BorrowedBook> booksOverDue = borrowedBooks.stream() // filtered list for adding fine
				.filter(b -> b.getReturnDate() != null && b.getReturnDate().isAfter(b.getDueDate()) && b.getFine() == 0)
				.toList();
		DatabaseService.addFine(booksOverDue);
		loadBorrowedBooks();
		initializeCol1(borrowedBooks);
		loadUnreturnedBooks();
		initializeCol2(unreturnedBooks);
	}

	private void loadUnreturnedBooks() {
		unreturnedBooks = DatabaseService.getUnreturnedBooks();
	}

	private void initializeCol(List<Book> books) {
		col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_pub_date.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
		col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		col_cate.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
		col_avail.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
		tbl_data.setItems(FXCollections.observableArrayList(books));
	}

	private void initializeCol1(List<BorrowedBook> books) {
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_card_id.setCellValueFactory(new PropertyValueFactory<>("cardId"));
		col_book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		col_borrowed_date.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
		col_due_date.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		col_return_date.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		col_fine.setCellValueFactory(new PropertyValueFactory<>("fine"));
		tbl_data1.setItems(FXCollections.observableArrayList(books));
	}

	private void initializeCol2(List<BorrowedBook> books) {
		col_id1.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_card_id1.setCellValueFactory(new PropertyValueFactory<>("cardId"));
		col_book_id1.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		col_borrowed_date1.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
		col_due_date1.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		col_fine1.setCellValueFactory(new PropertyValueFactory<>("fine"));
		tbl_data2.setItems(FXCollections.observableArrayList(books));
	}

	private void initializeColAuthTbl(List<Author> authors) {
		col_author_id.setCellValueFactory(new PropertyValueFactory<>("author_id"));
		col_author_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_author_country.setCellValueFactory(new PropertyValueFactory<>("country"));
		tbl_author.setItems(FXCollections.observableArrayList(authors));
	}

	private void initializeColCateTbl(List<Category> categories) {
		col_cate_id.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
		col_cate_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		tbl_cate.setItems(FXCollections.observableArrayList(categories));
	}

	private void initializeColLibrarians(List<Librarian> librarians){
		col_id_librarian.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
		col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
		col_nrc_no.setCellValueFactory(new PropertyValueFactory<>("nrcNo"));
		col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		col_creation_date.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
		tbl_librarians.setItems(FXCollections.observableArrayList(librarians));
	}

	@FXML
	void btn_add_new_on_click() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("addBook.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Add Book");
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	void btn_add_new_on_click1() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("addBorrowedBook.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Add Borrowed Book");
		stage.setResizable(false);
		stage.show();
	}

	private void loadBooks() {
		bookItems = DatabaseService.getAllBooks();
	}

	private void loadBorrowedBooks() {

		borrowedBooks = DatabaseService.getBorrowedBooks();
	}

	@FXML
	void btn_del_on_click() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Book?");
		alert.setContentText("Are you sure you want to delete this book?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == okButton) {
				boolean deleted = DatabaseService.deleteBook(book.getCode());
				if (deleted) {
					showAlert(AlertType.INFORMATION, "Book Deleted");
					initialize(null, null);
				} else {
					showAlert(AlertType.ERROR, "This book is borrowed by a member");
				}
			} else {
				alert.close();
			}
		});
	}

	@FXML
	void btn_return_on_click() {
		if (DatabaseService.returnBook(borrowedBook)) {
			showAlert(AlertType.INFORMATION, "Book returned");
			initialize(null, null);
		} else {
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void btn_save_on_click(ActionEvent event) {
		String title = txt_title.getText();
		LocalDate pubDate = cbo_pub_date.getValue();
		int author_index = cbo_auth.getSelectionModel().getSelectedIndex();
		int cat_index = cbo_cate.getSelectionModel().getSelectedIndex();

		if (title.isEmpty()) {
			showAlert(AlertType.WARNING, "Please enter title");
			return;
		}
		if (pubDate == null) {
			showAlert(AlertType.WARNING, "Please setup publish date");
			return;
		}
		if (author_index == -1) {
			showAlert(AlertType.WARNING, "Please choose author");
			return;
		}
		if (cat_index == -1) {
			showAlert(AlertType.WARNING, "Please choose category");
			return;
		}

		book.setTitle(title);
		book.setPublishedDate(pubDate);
		book.setAuthor(authorList.get(author_index));
		book.setCategory(categoryList.get(cat_index));

		System.out.println(book.getTitle());
		System.out.println(book.getCategory().getCategoryId() + ". " + book.getCategory().getName());
		System.out.println(book.getAuthor().getAuthor_id() + ". " + book.getAuthor().getName());
		System.out.println(book.getIsAvailable());
		System.out.println(book.getPublishedDate());

		if (DatabaseService.updateBook(book)) {
			showAlert(AlertType.INFORMATION, "Update success");
			initialize(null, null);
			txt_code.clear();
			txt_title.clear();
			cbo_auth.getSelectionModel().clearSelection();
			cbo_cate.getSelectionModel().clearSelection();
			cbo_pub_date.setValue(null);
		} else {
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	private void loadAuthors() {
		authorList = DatabaseService.getAllAuthors();
		List<String> names = authorList.stream().map(Author::getName).toList();
		cbo_auth.setItems(FXCollections.observableArrayList(names));
	}

	private void loadCategories() {
		categoryList = DatabaseService.getAllCategories();

		List<String> names = categoryList.stream().map(Category::getName).toList();
		cbo_cate.setItems(FXCollections.observableArrayList(names));
	}

	private void showAlert(Alert.AlertType type, String header) {
		Alert alert = new Alert(type);
		alert.setHeaderText(header);
		alert.show();
	}

	private void showAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Cannot Delete members.");
		alert.setContentText("This member hasn't returned books yet.");
		alert.show();
	}

	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnBorrowHistory) {
			initialize(null, null);
			pnlBorrowHistory.toFront();
		} else if (actionEvent.getSource() == btnHome) {
			initialize(null, null);
			pnlHome.toFront();
		} else if (actionEvent.getSource() == btnBorrowedBooks) {
			initialize(null, null);
			pnlBorrowedBooks.toFront();
		} else if (actionEvent.getSource() == btnAuthCate) {
			initialize(null, null);
			pnlAuthCate.toFront();
		} else if (actionEvent.getSource() == btnSignOut) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Signing Out?");
			alert.setContentText("Are you sure you want sign out?");
			ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
			ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
			alert.getButtonTypes().setAll(okButton, noButton);
			alert.showAndWait().ifPresent(type -> {
				if (type == okButton) {
					try {
						Main.changeScene("loginPage.fxml", "login Page", false);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					alert.close();
				}
			});

		} else if (actionEvent.getSource() == btnMembers) {
			initialize(null, null
			);
			pnlMembers.toFront();

		} else if (actionEvent.getSource() == btnLibrarians) {
			initialize(null, null);
			pnlLibrarians.toFront();
		}

	}

	@FXML
	public void clickItem() {
		book = tbl_data.getSelectionModel().getSelectedItem();
		txt_code.setText(String.valueOf(book.getCode()));
		txt_title.setText(book.getTitle());
		cbo_auth.getSelectionModel().select(book.getAuthorName());
		cbo_cate.getSelectionModel().select(book.getCategoryName());
		cbo_pub_date.setValue(book.getPublishedDate());
		System.out.println(book);
	}

	@FXML
	public void clickItem1() {
		borrowedBook = tbl_data2.getSelectionModel().getSelectedItem();
		System.out.println(borrowedBook);
	}

	@FXML
	void click_item_auth() {
		author = tbl_author.getSelectionModel().getSelectedItem();
		System.out.println(author);
		txt_author_name.setText(author.getName());
		txt_author_country.setText(author.getCountry());
	}

	@FXML
	void click_item_cate() {
		category = tbl_cate.getSelectionModel().getSelectedItem();
		System.out.println(category);
		txt_cate_name.setText(category.getName());
	}

	@FXML
	void searchBooks(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			List<Book> bookList = new ArrayList<>();

			for (Book book : bookItems) {

				String search = txt_search_book.getText().toLowerCase();
				System.out.println(search);
				String code = String.valueOf(book.getCode());
				String title = String.valueOf(book.getTitle()).toLowerCase();
				String authorName = book.getAuthorName().toLowerCase();
				String categoryName = book.getCategoryName().toLowerCase();
				String publishedDate = String.valueOf(book.getPublishedDate());

				if (code.contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (title.contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (authorName.contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (categoryName.contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (publishedDate != null && publishedDate.contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
			}
			if (bookList.size() > 0) // check if searched results exist
				initializeCol(bookList);
			else
				initializeCol(bookItems);
		}
	}

	@FXML
	void searchBBooks(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			List<BorrowedBook> bBookList = new ArrayList<>();
			String search = txt_search_bbook.getText();
			System.out.println(search);
			for (BorrowedBook bBook : borrowedBooks) {
				String id = String.valueOf(bBook.getId());
				String bookId = String.valueOf(bBook.getBookId());
				String cardId = String.valueOf(bBook.getCardId());
				String borrowedDate = String.valueOf(bBook.getBorrowDate());
				String dueDate = String.valueOf(bBook.getDueDate());
				String returnDate = String.valueOf(bBook.getReturnDate());

				if (id.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

				if (bookId.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

				if (cardId.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

				if (borrowedDate != null && borrowedDate.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

				if (dueDate != null && dueDate.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

				if (returnDate != null && returnDate.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

			}
			if (bBookList.size() > 0)  // check if search results exist
				initializeCol1(bBookList);
			else
				initializeCol1(borrowedBooks);
		}
	}

	@FXML
	void searchUnreturnedBooks(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			List<BorrowedBook> bBookList = new ArrayList<>();
			String search = txt_search_unreturned_books.getText();
			System.out.println(search);
			for (BorrowedBook bBook : unreturnedBooks) {
				String id = String.valueOf(bBook.getId());
				String bookId = String.valueOf(bBook.getBookId());
				String cardId = String.valueOf(bBook.getCardId());
				String borrowedDate = String.valueOf(bBook.getBorrowDate());
				String dueDate = String.valueOf(bBook.getDueDate());

					if (id.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

					if (bookId.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

					if (cardId.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

					if (borrowedDate != null && borrowedDate.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

					if (dueDate != null && dueDate.contains(search) && !bBookList.contains(bBook))
						bBookList.add(bBook);

			}
			if (bBookList.size() > 0) { // check if search results exist
				initializeCol2(bBookList);
			} else
				initializeCol2(unreturnedBooks);
		}
	}

	@FXML
	void btn_save_on_click_author() {
		String name = txt_author_name.getText();
		String country = txt_author_country.getText();

		if (name.isEmpty()) {
			showAlert(AlertType.WARNING, "Please enter name");
			return;
		}

		if (country.isEmpty()) {
			showAlert(AlertType.WARNING, "Please enter country");
			return;
		}

		author.setName(name);
		author.setCountry(country);

		if (DatabaseService.updateAuthor(author)) {
			showAlert(AlertType.INFORMATION, "Update success");
			initialize(null, null);
			txt_author_name.clear();
			txt_author_country.clear();
		} else {
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void btn_save_on_click_cate() {
		String name = txt_cate_name.getText();

		if (name.isEmpty()) {
			showAlert(AlertType.WARNING, "Please enter name");
			return;
		}
		category.setName(name);

		if (DatabaseService.updateCategory(category)) {
			showAlert(AlertType.INFORMATION, "Update success");
			initialize(null, null);
			txt_cate_name.clear();
		} else {
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void btn_add_new_on_click_auth() {
		Author auth = new Author();
		String name = txt_author_name.getText();
		String country = txt_author_country.getText();

		if (name.isEmpty()) {
			showAlert(AlertType.WARNING, "Please enter name");
			return;
		}

		if (country.isEmpty()) {
			showAlert(AlertType.WARNING, "Please enter country");
			return;
		}

		auth.setName(name);
		auth.setCountry(country);

		if (DatabaseService.addNewAuthor(auth)) {
			showAlert(AlertType.INFORMATION, "A new author is addedd");
			initialize(null, null);
			txt_author_name.clear();
			txt_author_country.clear();
		} else {
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void btn_add_new_on_click_cate() {
		Category cate = new Category();
		String name = txt_cate_name.getText();

		if (name.isEmpty()) {
			showAlert(AlertType.WARNING, "Please enter name");
			return;
		}
		cate.setName(name);
		
		if(DatabaseService.addNewCategory(cate)) {
			showAlert(AlertType.INFORMATION, "A new category is added");
			initialize(null, null);

			txt_cate_name.clear();
		}
		else {
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void searchAuthors(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER){
			List<Author> authors = new ArrayList<>();
			String search = txt_search_authors.getText().toLowerCase();
			System.out.println(search);
			for(Author auth : authorList){
				String id = String.valueOf(auth.getAuthor_id());
				String name = auth.getName().toLowerCase();
				String country = auth.getCountry().toLowerCase();
					if(id.contains(search) && !authors.contains(auth))
						authors.add(auth);

					if(name.contains(search) && !authors.contains(auth))
						authors.add(auth);

					if(country.contains(search) && !authors.contains(auth))
						authors.add(auth);

				if (authors.size() > 0)  // check if search results exist
					initializeColAuthTbl(authors);
				else
					initializeColAuthTbl(authorList);
			}
		}
	}

	@FXML
	void searchCate(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER){
			List<Category> categories = new ArrayList<>();
			String search = txt_search_cate.getText().toLowerCase();
			System.out.println(search);
			for(Category cate : categoryList){
				String id = String.valueOf(cate.getCategoryId());
				String name = cate.getName().toLowerCase();

					if(id.contains(search) && !categories.contains(cate))
						categories.add(cate);

					if(name.contains(search) && !categories.contains(cate))
						categories.add(cate);

				if (categories.size() > 0)  // check if search results exist
					initializeColCateTbl(categories);
				else
					initializeColCateTbl(categoryList);
			}
		}
	}

	@FXML
	void searchLibrarian(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER){
			List<Librarian> librarianList = new ArrayList<>();
			String search = txt_search_librarian.getText().toLowerCase();
			System.out.println(search);
			for(Librarian lib : librarians){
				String id = String.valueOf(lib.getId());
				String username = lib.getUsername().toLowerCase();
				String nrcNo = lib.getNrcNo().toLowerCase();
				String phone = lib.getPhone();
				String creationDate = String.valueOf(lib.getCreatedAt());


				if(id.contains(search) && !librarianList.contains(lib))
					librarianList.add(lib);
				if(username.contains(search) && !librarianList.contains(lib))
					librarianList.add(lib);
				if(nrcNo.contains(search) && !librarianList.contains(lib))
					librarianList.add(lib);
				if(phone.contains(search) && !librarianList.contains(lib))
					librarianList.add(lib);
				if(creationDate.contains(search) && !librarianList.contains(lib))
					librarianList.add(lib);


				if (librarianList.size() > 0) // check if search results exist
					initializeColLibrarians(librarianList);
				 else
					initializeColLibrarians(librarians);
			}
		}
	}

	@FXML
	void btn_update_on_click_librarian() {

		librarian.setUsername(txt_username.getText());
		librarian.setPassword(txt_password.getText());
		librarian.setNrcNo(txt_nrc_no.getText());
		librarian.setPhone(txt_phone.getText());
		librarian.setCreatedAt(date_creation_date.getValue());

		if(DatabaseService.updateLibrarian(librarian)){
			showAlert(AlertType.INFORMATION, "Librarian updated");
			initialize(null, null);
		}else{
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void btn_del_on_click_librarian() {
		if(DatabaseService.deleteLibrarian(librarian.getId())){
			showAlert(AlertType.INFORMATION, "Librarian deleted");
			initialize(null, null);
		}else
			showAlert(AlertType.ERROR, "Something went wrong");
	}

	@FXML
	void btn_add_new_on_click_member() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("addMember.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Add Librarian");
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	void btn_update_on_click_member() {
		member.setName(txt_name_mem.getText());
		member.setRollNo(txt_roll_no_mem.getText());
		member.setAcademicYear(Year.parse(txt_academic_year_mem.getText()));
		member.setClassYear(txt_class_year_mem.getText());
		member.setCreatedAt(date_creation_date_mem.getValue());
		member.setExpireAt(date_expire_date_mem.getValue());
		if(DatabaseService.updateMember(member)){
			showAlert(AlertType.INFORMATION, "Member updated");
			initialize(null, null);
		}
		else{
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void searchMember(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER){
			List<Member> memberList = new ArrayList<>();
			String search = txt_search_member.getText().toLowerCase();
			System.out.println(search);
			for(Member mem : members){
				String name = mem.getName().toLowerCase();
				String rollNo = mem.getRollNo().toLowerCase();
				String classYear = mem.getClassYear().toLowerCase();
				String academicYear = String.valueOf(mem.getAcademicYear());
				String creationDate = String.valueOf(mem.getCreatedAt());
				String expireDate = String.valueOf(mem.getExpireAt());

				if(name.contains(search) && !memberList.contains(mem))
					memberList.add(mem);
				if(rollNo.contains(search) && !memberList.contains(mem))
					memberList.add(mem);
				if(classYear.contains(search) && !memberList.contains(mem))
					memberList.add(mem);
				if(academicYear.contains(search) && !memberList.contains(mem))
					memberList.add(mem);
				if(creationDate.contains(search) && !memberList.contains(mem))
					memberList.add(mem);
				if(expireDate.contains(search) && !memberList.contains(mem))
					memberList.add(mem);


				if (memberList.size() > 0) // check if search results exist
					initializeColMembers(memberList);
				else
					initializeColMembers(members);
			}
		}

	}

	@FXML
	void btn_del_on_click_member(){


		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Book?");
		alert.setContentText("Are you sure you want to delete this book?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
			boolean hasNotReturned = false;

			if (type == okButton) {
				for(BorrowedBook borrowedBook: unreturnedBooks){
					if(borrowedBook.getCardId() == member.getCardId()) {
						hasNotReturned = true;
						break;
					}
				}
				if(!hasNotReturned){
					if(DatabaseService.deleteMember(member.getCardId())){
						showAlert(AlertType.INFORMATION, "Member Deleted");
						initialize(null, null);
					}
					else{
						showAlert(AlertType.ERROR, "Something went wrong");
					}
				}
				else{
					showAlert();
				}
			} else {
				alert.close();
			}
		});



	}

	@FXML
	void click_item_member() {
		member = tbl_members.getSelectionModel().getSelectedItem();
		System.out.println(member);
		txt_card_id_mem.setText(String.valueOf(member.getCardId()));
		txt_name_mem.setText(member.getName());
		txt_roll_no_mem.setText(member.getRollNo());
		txt_academic_year_mem.setText(String.valueOf(member.getAcademicYear()));
		txt_class_year_mem.setText(member.getClassYear());
		date_creation_date_mem.setValue(member.getCreatedAt());
		date_expire_date_mem.setValue(member.getExpireAt());
	}

	@FXML
	void btn_add_new_on_click_librarian() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("addLibrarian.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Add Librarian");
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	void click_item_librarian() {
		librarian = tbl_librarians.getSelectionModel().getSelectedItem();
		System.out.println(librarian);
		txt_id.setText(String.valueOf(librarian.getId()));
		txt_username.setText(librarian.getUsername());
		txt_password.setText(librarian.getPassword());
		txt_nrc_no.setText(librarian.getNrcNo());
		txt_phone.setText(librarian.getPhone());
		date_creation_date.setValue(librarian.getCreatedAt());
	}

}
