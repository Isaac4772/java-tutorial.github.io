package bms.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bms.BMS_Main;
import bms.model.entity.Category;
import bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryController implements Initializable{

	 @FXML
	    private TableColumn<Category, Integer> col_id;

	    @FXML
	    private TableColumn<Category, String> col_name;

	    @FXML
	    private TableView<Category> tbl_categories;

	    @FXML
	    private TextField txt_id;

	    @FXML
	    private TextField txt_name;

	    @FXML
	    void btn_add_click(ActionEvent event) {

	    }

	    @FXML
	    void btn_back_click(ActionEvent event) throws IOException {
	    	BMS_Main.changeScene("view/Main.fxml");
	    }

	    @FXML
	    void btn_update_click(ActionEvent event) {

	    }

	    @FXML
	    void txt_reset_click(ActionEvent event) {
	    	txt_id.setText(null);
	    	txt_name.setText(null);
	    	tbl_categories.getSelectionModel().clearSelection();
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
			
			loadCategories();
			
		}

		private void loadCategories() {
			
			List<Category> list = DatabaseService.getAllCategories();
			
			tbl_categories.setItems(FXCollections.observableArrayList(list));
			
		}
}
