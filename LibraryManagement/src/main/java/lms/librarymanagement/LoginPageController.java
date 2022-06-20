package lms.librarymanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lms.librarymanagement.model.entity.Librarian;
import lms.librarymanagement.model.services.DatabaseService;

import java.io.IOException;
import java.util.List;

public class LoginPageController {

	@FXML
	private Label lbl_error;

	@FXML
	private PasswordField txt_password;

	@FXML
	private TextField txt_username;

	@FXML
	void btn_login_onclick() throws IOException {

		List<Librarian> librarians = DatabaseService.getAllLibrarians();

		for (Librarian librarian : librarians) {
			if (txt_username.getText().equals(librarian.getUsername()) && txt_password.getText().equals(librarian.getPassword())) {
				System.out.println(librarian.getUsername());
				System.out.println(librarian.getPassword());
				Main.changeScene("home.fxml", "Libraeo", false);
				return;
			}
		}
		lbl_error.setText("Username or password is wrong!");
	}

		@FXML
		void focus_next_textField (KeyEvent event){
			if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)
				txt_password.requestFocus();
		}

		@FXML
		void login_on_enter (KeyEvent event) throws IOException {
			if (event.getCode() == KeyCode.ENTER)
				btn_login_onclick();
		}
	}
