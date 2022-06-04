package bms.controller;

import java.io.IOException;

import bms.BMS_Main;
import javafx.fxml.FXML;

public class MainController {
	
	@FXML
	public void book_click() {
		try {
			BMS_Main.changeScene("view/Books.fxml");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	@FXML
	public void author_click() throws IOException {
		BMS_Main.changeScene("view/Authors.fxml");
	}
	@FXML
	public void category_click() throws IOException {
		BMS_Main.changeScene("view/Categories.fxml");
	}
}
