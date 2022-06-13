package lms.librarymanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class LoginPageController {

	@FXML
	private Label lbl_error;

	@FXML
	private PasswordField txt_password;

	@FXML
	private TextField txt_username;
	
	private String username = "admin";
	private String password = "admin123";

	@FXML
	void btn_login_onclick() throws IOException {
		

		if (txt_username.getText().equals(username) && txt_password.getText().equals(password)) {
			Main.changeScene("home.fxml", "Home", false);
		} else
			lbl_error.setText("Username or password is wrong!");
	}
	
	@FXML
    void focus_next_textField(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) 
			txt_password.requestFocus();
    }
	
	 @FXML
	    void login_on_enter(KeyEvent event) throws IOException {
		 	if(event.getCode() == KeyCode.ENTER) 
		 		btn_login_onclick();
	    }

}
