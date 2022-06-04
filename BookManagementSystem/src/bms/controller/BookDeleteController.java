package bms.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import bms.BMS_Main;
import bms.model.entity.Book;
import bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookDeleteController implements Initializable{

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
    void btn_back_click(ActionEvent event) throws IOException {
    	BMS_Main.changeScene("view/Books.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
		col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_publish.setCellValueFactory(new PropertyValueFactory<>("publish_date"));
		
		loadBooks();
		/*tbl_data.getSelectionModel().selectedItemProperty().addListener(
			(obs, oldSelection, newSelection) ->{
				if(newSelection != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirm Message");
					alert.setHeaderText(null);
					alert.setContentText("Are you sure to delete?");
					Optional<ButtonType> result = alert.showAndWait();
					if(result.get() == ButtonType.OK) {
						//delete
						Book selectBook = tbl_data.getSelectionModel().getSelectedItem();
						if(DatabaseService.deleteBookByCode(selectBook.getCode())) {
							loadBooks();
							//alert.close();
						}
					}
					alert.close();
				}
			}
				
		);*/
		
	}
	@FXML
    void btn_delete_click(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Message");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to delete?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			//delete
			Book selectBook = tbl_data.getSelectionModel().getSelectedItem();
			if(DatabaseService.deleteBookByCode(selectBook.getCode())) {
				loadBooks();
				//alert.close();
			}
		}
    }
	private void loadBooks() {
		
		List<Book> bookList = DatabaseService.getAllBooks();
		
		tbl_data.setItems(FXCollections.observableArrayList(bookList));
		
	}
}
