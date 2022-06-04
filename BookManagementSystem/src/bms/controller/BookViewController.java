package bms.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import bms.BMS_Main;
import bms.model.entity.Book;
import bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookViewController implements Initializable{

	@FXML
    private TableColumn<Book, String> col_author;

    @FXML
    private TableColumn<Book, String> col_category;

    @FXML
    private TableColumn<Book, Integer> col_code;

    @FXML
    private TableColumn<Book, LocalDate> col_date;

    @FXML
    private TableColumn<Book, String> col_title;

    @FXML
    private TableView<Book> tbl_data;

    @FXML
    private TextField txt_author;

    @FXML
    private TextField txt_category;

    @FXML
    private DatePicker txt_publish;

    @FXML
    void btn_back_click(ActionEvent event) throws IOException {
    	BMS_Main.changeScene("view/Books.fxml");
    }

    @FXML
    void btn_search_click(ActionEvent event) {
    	LocalDate pub_date = txt_publish.getValue();
    	String author = txt_author.getText();
    	String category = txt_category.getText();
    	
    	List<Book> bookList = DatabaseService.findAllBooks(pub_date,author,category);
    	if(bookList.size() > 0) {
    		tbl_data.setItems(FXCollections.observableArrayList(bookList));
    	}
    	else {
    		tbl_data.setItems(null);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("publish_date"));
		col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
	}
}
