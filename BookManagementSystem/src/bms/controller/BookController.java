package bms.controller;

import java.io.IOException;

import bms.BMS_Main;
import javafx.fxml.FXML;

public class BookController {

	@FXML
	public void btn_home_click() throws IOException {
		BMS_Main.changeScene("view/Main.fxml");
	}
	
	@FXML
	public void btn_addBook_click() throws IOException {
		BMS_Main.changeScene("view/AddBook.fxml");
	}
	@FXML
	public void btn_editBook_click() throws IOException {
		BMS_Main.changeScene("view/EditBook.fxml");
	}
	@FXML
	public void btn_viewBook_click() throws IOException {
		BMS_Main.changeScene("view/ViewBook.fxml");
	}
	@FXML
	public void btn_deleteBook_click() throws IOException {
		BMS_Main.changeScene("view/DeleteBook.fxml");
	}
	
	
	
}
