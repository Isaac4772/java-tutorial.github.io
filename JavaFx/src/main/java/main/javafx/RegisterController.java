package main.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RegisterController {
    @FXML
    public void btn_login_click() throws IOException {
        Main.changeScene("Sample.fxml");
    }

    public void btn_register_click(ActionEvent actionEvent) {
    }
}
