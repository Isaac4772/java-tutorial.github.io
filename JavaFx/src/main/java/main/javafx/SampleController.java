package main.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class SampleController {
    @FXML
    private TextField txt_name;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label lbl_result;
    @FXML
    public void btn_login_click(){
        String name = txt_name.getText();
        String pass = txt_password.getText();

        if(name.isEmpty() || pass.isEmpty()){
            lbl_result.setText("Please fill all data");
        }
        else if(name.equalsIgnoreCase("admin") && pass.equals("admin123")){
            lbl_result.setText("Login success");
        }
        else{
            lbl_result.setText("Username and password do not match");
        }
    }

    @FXML
    public void btn_register_click() throws IOException {
        Main.changeScene("Register.fxml");
    }
}