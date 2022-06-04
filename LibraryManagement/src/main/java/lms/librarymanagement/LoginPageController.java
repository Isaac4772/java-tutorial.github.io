package lms.librarymanagement;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
public class LoginPageController {

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private Text txt_error;



    @FXML
    void btn_login_onclick() throws IOException {
        String username = "admin";
        String password = "admin123";

        if(txt_username.getText().equals(username) && txt_password.getText().equals(password)) {
            Main.changeScene("home.fxml", "Home", false);
        } else
            txt_error.setText("Username or password is wrong!");
    }


}
