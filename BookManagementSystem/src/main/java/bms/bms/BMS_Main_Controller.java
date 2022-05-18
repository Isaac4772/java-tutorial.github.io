package bms.bms;

import javafx.fxml.FXML;

import java.io.IOException;

public class BMS_Main_Controller {
    @FXML
    public void books_click() throws IOException {
        BMS_Main.changeScene("Books.fxml");
    }

    @FXML
    public void authors_click(){

    }

    @FXML
    public void categories_click(){

    }
}