package bms.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bms.BMS_Main;
import bms.model.entity.Author;
import bms.model.entity.Book;
import bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuthorController implements Initializable{

	@FXML
    private TableColumn<Author, String> col_country;

    @FXML
    private TableColumn<Author, Integer> col_id;

    @FXML
    private TableColumn<Author, String> col_name;

    @FXML
    private TableView<Author> tbl_authors;

    @FXML
    private TextField txt_country;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    void btn_add_click(ActionEvent event) {
    	String name = txt_name.getText();
    	String country = txt_country.getText();
    	DatabaseService.saveAuthor(name,country);
    	loadAuthors();
    }

    @FXML
    void btn_back_click(ActionEvent event) throws IOException {
    	BMS_Main.changeScene("view/Main.fxml");
    }
    
    @FXML
    void btn_reset_click() {
    	txt_country.setText(null);
    	txt_name.setText(null);
    	txt_id.setText(null);
    	tbl_authors.getSelectionModel().clearSelection();
    }

    @FXML
    void btn_update_click(ActionEvent event) {
    	Author author = new Author();
    	author.setCountry(txt_country.getText());
    	author.setName(txt_name.getText());
    	author.setId(Integer.parseInt(txt_id.getText()));
    	
    	DatabaseService.updateAuthor(author);
    	loadAuthors();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		col_country.setCellValueFactory(new PropertyValueFactory<>("country"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		loadAuthors();
		
		tbl_authors.getSelectionModel().selectedItemProperty().addListener(
				
		(obs, oldSelect, newSelect) -> {
			if(newSelect != null) {
				Author auth = tbl_authors.getSelectionModel().getSelectedItem();
				
				txt_country.setText(auth.getCountry());
				txt_name.setText(auth.getName());
				txt_id.setText(String.valueOf(auth.getId()));
			}
		}
				
		);
		
	}

	private void loadAuthors() {
		
		List<Author> authorList = DatabaseService.getAllAuthors();
		
		tbl_authors.setItems(FXCollections.observableArrayList(authorList));
		
	}
}
