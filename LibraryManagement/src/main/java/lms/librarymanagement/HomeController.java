package lms.librarymanagement;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Author;
import lms.librarymanagement.model.entity.Book;
import lms.librarymanagement.model.entity.BorrowedBook;
import lms.librarymanagement.model.entity.Category;
import lms.librarymanagement.model.services.DatabaseService;

public class HomeController implements Initializable {

	 @FXML
	    private ToggleButton btnAuthors;

	    @FXML
	    private ToggleButton btnBorrowHistory;

	    @FXML
	    private ToggleButton btnBorrowedBooks;

	    @FXML
	    private ToggleButton btnCategories;

	    @FXML
	    private ToggleButton btnHome;

	    @FXML
	    private ToggleButton btnLibrarians;

	    @FXML
	    private ToggleButton btnMembers;

	    @FXML
	    private ToggleButton btnSignOut;
    
    @FXML
    private ToggleGroup btn_toggle;

	@FXML
	private Button btn_add_new;

	@FXML
	private Button btn_add_new1;

	@FXML
	private TableColumn<Book, String> col_author;

	@FXML
	private TableColumn<Book, Integer> col_avail;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_book_id;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_borrowed_date;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_card_id;

	@FXML
	private TableColumn<Book, String> col_cate;

	@FXML
	private TableColumn<Book, Integer> col_code;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_due_date;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_fine;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_id;

	@FXML
	private TableColumn<Book, LocalDate> col_pub_date;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_return_date;

	@FXML
	private TableColumn<Book, String> col_title;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_due_date1;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_fine1;

	@FXML
	private TableColumn<BorrowedBook, Integer> col_id1;
	@FXML
	private TableColumn<BorrowedBook, Integer> col_book_id1;
	@FXML
	private TableColumn<BorrowedBook, Integer> col_card_id1;

	@FXML
	private TableColumn<BorrowedBook, LocalDate> col_borrowed_date1;
	@FXML
	private Pane pnlBorrowedBooks;

	@FXML
	private Pane pnlBorrowHistory;

	@FXML
	private Pane pnlMenus;

	@FXML
	private Pane pnlHome;

	@FXML
	private TableView<Book> tbl_data; // All Books (Home)

	@FXML
	private TableView<BorrowedBook> tbl_data1; // Borrow History

	@FXML
	private TableView<BorrowedBook> tbl_data2; // Borrowed books that haven't been return yet

	@FXML
	private Button btn_auth;

	@FXML
	private Button btn_cate;

	@FXML
	private Button btn_return;

	@FXML
	private ComboBox<String> cbo_auth;

	@FXML
	private ComboBox<String> cbo_cate;

	@FXML
	private DatePicker cbo_pub_date;

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_save;
	@FXML
	private Button btn_del;
	@FXML
	private TextField txt_code;
	@FXML
	private TextField txt_title;
	@FXML
	private TextField txt_id;
	@FXML
	private TextField txt_book_id;
	@FXML
	private TextField txt_card_id;
	@FXML
	private TextField txt_search_bbook;
	@FXML
	private TextField txt_search_book;
	@FXML
	private TextField txt_search_unreturned_books;
	@FXML
	private DatePicker cbo_borrow_date;
	@FXML
	private DatePicker cbo_due_date;
	@FXML
	private DatePicker cbo_return_date;

	private List<Author> authorList;
	private List<Category> categoryList;
	private static Book book;
	private static BorrowedBook borrowedBook;

	private static List<Book> bookItems;
	private static List<BorrowedBook> borrowedBooks;
	private static List<BorrowedBook> unreturnedBooks;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadCategories();
		loadAuthors();
		loadBooks();
		initializeCol(); // For Books table
		loadBorrowedBooks();
		initializeCol1(); // For Borrow History table
		loadUnreturnedBooks();
		initializeCol2(); // For Unreturned (Borrowed) Book table
	}

	private void loadUnreturnedBooks() {
		unreturnedBooks = DatabaseService.getUnreturnedBooks();
	}

	private void initializeCol() {
		col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_pub_date.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
		col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		col_cate.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
		col_avail.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
		tbl_data.setItems(FXCollections.observableArrayList(bookItems));
	}

	private void initializeCol1() {
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_card_id.setCellValueFactory(new PropertyValueFactory<>("cardId"));
		col_book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		col_borrowed_date.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
		col_due_date.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		col_return_date.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		col_fine.setCellValueFactory(new PropertyValueFactory<>("fine"));
		tbl_data1.setItems(FXCollections.observableArrayList(borrowedBooks));
	}

	private void initializeCol2() {
		col_id1.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_card_id1.setCellValueFactory(new PropertyValueFactory<>("cardId"));
		col_book_id1.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		col_borrowed_date1.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
		col_due_date1.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		col_fine1.setCellValueFactory(new PropertyValueFactory<>("fine"));
		tbl_data2.setItems(FXCollections.observableArrayList(unreturnedBooks));
	}

	@FXML
	void btn_add_new_on_click(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("addBook.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Add Book");
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	void btn_add_new_on_click1(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("addBorrowedBook.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Add Book");
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
	void btn_del_on_click(ActionEvent event) {
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
					showAlert(AlertType.ERROR, "Book Deletion failed", "This book is borrowed by a member");
				}
			} else {
				alert.close();
			}
		});
	}

	@FXML
	void btn_return_on_click(ActionEvent event) throws IOException {
		if (DatabaseService.returnBook(borrowedBook)) {
			showAlert(AlertType.INFORMATION, "Book returned");
			initialize(null, null);
		} else {
			showAlert(AlertType.ERROR, "Something went wrong");
		}
	}

	@FXML
	void btn_save_on_click(ActionEvent event) throws IOException {
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
			showAlert(AlertType.ERROR, "Cannot Update Book", "Something went wrong");
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
		alert.setTitle("Message");
		alert.setHeaderText(header);
		alert.show();
	}

	private void showAlert(Alert.AlertType type, String header, String msg) {
		Alert alert = new Alert(type);
		alert.setTitle("Message");
		alert.setContentText(msg);
		alert.setHeaderText(header);
		alert.show();
	}

	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnBorrowHistory) {
			initialize(null, null);
			pnlBorrowHistory.toFront();
		}
		else if (actionEvent.getSource() == btnHome) {
			initialize(null, null);
			pnlHome.toFront();
		}
		else if (actionEvent.getSource() == btnBorrowedBooks) {
			initialize(null, null);
			pnlBorrowedBooks.toFront();
		}
		else if (actionEvent.getSource() == btnAuthors) {

		}
		else if (actionEvent.getSource() == btnCategories) {

		}
		else if (actionEvent.getSource() == btnSignOut) {
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
			
		}
		else if (actionEvent.getSource() == btnMembers) {

		}
		else if (actionEvent.getSource() == btnLibrarians) {

		}
		
		
	}

	@FXML
	public void clickItem(MouseEvent event) {
		book = tbl_data.getSelectionModel().getSelectedItem();
		txt_code.setText(String.valueOf(book.getCode()));
		txt_title.setText(book.getTitle());
		cbo_auth.getSelectionModel().select(book.getAuthorName());
		cbo_cate.getSelectionModel().select(book.getCategoryName());
		cbo_pub_date.setValue(book.getPublishedDate());
		System.out.println(book);
	}

	@FXML
	public void clickItem1(MouseEvent event) {
		borrowedBook = tbl_data2.getSelectionModel().getSelectedItem();
		System.out.println(borrowedBook);
	}

	@FXML
	void searchBooks(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			List<Book> bookList = new ArrayList<>();
			String search = txt_search_book.getText();
			System.out.println(search);
			for (Book book : bookItems) {
				if (String.valueOf(book.getCode()) != null && String.valueOf(book.getCode()).contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (String.valueOf(book.getTitle()) != null && String.valueOf(book.getTitle()).contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (book.getAuthorName() != null && book.getAuthorName().contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (book.getCategoryName() != null && book.getCategoryName().contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
				if (String.valueOf(book.getPublishedDate()) != null
						&& String.valueOf(book.getPublishedDate()).contains(search)) {
					if (!bookList.contains(book))
						bookList.add(book);
				}
			}
			if (bookList.size() > 0) { // check if searched results exist
				col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
				col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
				col_pub_date.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
				col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
				col_cate.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
				col_avail.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
				tbl_data.setItems(FXCollections.observableArrayList(bookList));
			} else
				initializeCol();
		}
	}

	@FXML
	void searchBBooks(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			List<BorrowedBook> bBookList = new ArrayList<>();
			String search = txt_search_bbook.getText();
			System.out.println(search);
			for (BorrowedBook bBook : borrowedBooks) {
				if (String.valueOf(bBook.getId()) != null && String.valueOf(bBook.getId()).contains(search)) {
					bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getBookId()) != null && String.valueOf(bBook.getBookId()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getCardId()) != null && String.valueOf(bBook.getCardId()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getBorrowDate()) != null
						&& String.valueOf(bBook.getBorrowDate()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getDueDate()) != null && String.valueOf(bBook.getDueDate()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getReturnDate()) != null
						&& String.valueOf(bBook.getReturnDate()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
			}
			if (bBookList.size() > 0) { // check if search results exist
				col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
				col_card_id.setCellValueFactory(new PropertyValueFactory<>("cardId"));
				col_book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
				col_borrowed_date.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
				col_due_date.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
				col_return_date.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
				col_fine.setCellValueFactory(new PropertyValueFactory<>("fine"));
				tbl_data1.setItems(FXCollections.observableArrayList(bBookList));
			} else
				initializeCol1();
		}
	}

	@FXML
	void searchUnreturnedBooks(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			List<BorrowedBook> bBookList = new ArrayList<>();
			String search = txt_search_unreturned_books.getText();
			System.out.println(search);
			for (BorrowedBook bBook : unreturnedBooks) {
				if (String.valueOf(bBook.getId()) != null && String.valueOf(bBook.getId()).contains(search)) {
					bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getBookId()) != null && String.valueOf(bBook.getBookId()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getCardId()) != null && String.valueOf(bBook.getCardId()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getBorrowDate()) != null
						&& String.valueOf(bBook.getBorrowDate()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
				if (String.valueOf(bBook.getDueDate()) != null && String.valueOf(bBook.getDueDate()).contains(search)) {
					if (!bBookList.contains(bBook))
						bBookList.add(bBook);
				}
			}
			if (bBookList.size() > 0) { // check if search results exist
				col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
				col_card_id.setCellValueFactory(new PropertyValueFactory<>("cardId"));
				col_book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
				col_borrowed_date.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
				col_due_date.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
				col_return_date.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
				col_fine.setCellValueFactory(new PropertyValueFactory<>("fine"));
				tbl_data1.setItems(FXCollections.observableArrayList(bBookList));
			} else
				initializeCol2();
		}
	}

}
