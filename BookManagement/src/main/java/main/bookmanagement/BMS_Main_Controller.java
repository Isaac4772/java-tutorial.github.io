package main.bookmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BMS_Main_Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}