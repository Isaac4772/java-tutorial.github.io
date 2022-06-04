package bms.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import bms.BMS_Main;
import bms.model.entity.Author;
import bms.model.entity.Book;
import bms.model.entity.Category;
import bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookSaveController implements Initializable{

	@FXML
    private ComboBox<String> cbo_author;

    @FXML
    private ComboBox<String> cbo_category;

    @FXML
    private TableColumn<Book, String> col_author;

    @FXML
    private TableColumn<Book, String> col_category;

    @FXML
    private TableColumn<Book, Integer> col_code;

    @FXML
    private TableColumn<Book, LocalDate> col_publish;

    @FXML
    private TableColumn<Book, String> col_title;

    @FXML
    private TableView<Book> tbl_data;

    @FXML
    private TextField txt_code;

    @FXML
    private DatePicker txt_publish;

    @FXML
    private TextField txt_title;
    
    private List<Author> authorList;
    private List<Category> categoryList;
    

    @FXML
    void btn_clear_click(ActionEvent event) {
    	txt_code.setText(null);
    	txt_title.setText(null);
    	txt_publish.setValue(null);
    }

    @FXML
    void btn_home_click(ActionEvent event) throws IOException {
    	BMS_Main.changeScene("view/Books.fxml");
    }

    @FXML
    void btn_save_click(ActionEvent event) {
    	String code = txt_code.getText();
    	String title = txt_title.getText();
    	LocalDate pub_date = txt_publish.getValue();
    	int author_index = cbo_author.getSelectionModel().getSelectedIndex();
    	int category_index = cbo_category.getSelectionModel().getSelectedIndex();
    	
    	if(code.isEmpty()) {
    		showAlert(AlertType.WARNING, "Please enter code");
    		return;
    	}
    	if(title.isEmpty()) {
    		showAlert(AlertType.WARNING,"Please enter title");
    		return;
    	}
    	if(pub_date == null) {
    		showAlert(AlertType.WARNING,"Please setup publish date");
    		return;
    	}
    	if(author_index == -1) {
    		showAlert(AlertType.WARNING,"Please choose author");
    		return;
    	}
    	if(category_index == -1) {
    		showAlert(AlertType.WARNING,"Please choose category");
    		return;
    	}
    	
    	// save book
    	Book book = new Book();
    	book.setCode(Integer.parseInt(code));
    	book.setTitle(title);
    	book.setPublish_date(pub_date);
    	book.setCategory(categoryList.get(category_index));
    	book.setAuthor(authorList.get(author_index));
    	
    	if(DatabaseService.saveBook(book)) {
    		tbl_data.getItems().add(book);
    	}
    	else {
    		showAlert(AlertType.ERROR, "Something is wrong.Please try again!");
    	}
    	
    }

	private void showAlert(AlertType type, String body) {
		Alert alert = new Alert(type);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText(body);
		alert.showAndWait();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadAuthors();
		loadCategories();
		loadBooks();
		
	}

	private void loadBooks() {
		col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_publish.setCellValueFactory(new PropertyValueFactory<>("publish_date"));
		col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
		
		List<Book> bookList = DatabaseService.getAllBooks();
		
		tbl_data.setItems(FXCollections.observableArrayList(bookList));
		
	}

	private void loadCategories() {
		categoryList = DatabaseService.getAllCategories();
		
		List<String> names = categoryList.stream()
									.map(obj-> obj.getName())
									.collect(Collectors.toList());
		cbo_category.setItems(FXCollections.observableArrayList(names));
		
	}

	private void loadAuthors() {
		
		authorList = DatabaseService.getAllAuthors();
		
		List<String> names = authorList.stream()
										.map(obj -> obj.getName())
										.collect(Collectors.toList());
		
		cbo_author.setItems(FXCollections.observableArrayList(names));
		
	}
}
