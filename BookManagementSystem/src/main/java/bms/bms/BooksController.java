package bms.bms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class BooksController {
    @FXML
    public void add_book_click(ActionEvent actionEvent) throws IOException {
        BMS_Main.changeScene("AddBook.fxml");
    }
    @FXML
    public void edit_book_click(ActionEvent actionEvent) throws IOException {
        BMS_Main.changeScene("EditBook.fxml");
    }
    @FXML
    public void view_books_click(ActionEvent actionEvent) throws IOException {
        BMS_Main.changeScene("ViewBooks.fxml");
    }
    @FXML
    public void delete_book_click(ActionEvent actionEvent) {
    }
    @FXML
    public void home_click(ActionEvent actionEvent) throws IOException {
        BMS_Main.changeScene("BMS_Main.fxml");
    }
}
