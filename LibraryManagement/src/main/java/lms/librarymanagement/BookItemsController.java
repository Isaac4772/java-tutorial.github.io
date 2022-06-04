package lms.librarymanagement;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Book;
import java.io.IOException;

public class BookItemsController{

    @FXML
    private HBox itemC;

    @FXML
    private Label lbl_author;

    @FXML
    private Label lbl_category;

    @FXML
    private Label lbl_code;

    @FXML
    private Label lbl_published_date;

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_is_available;

    @FXML
    void btn_edit_book() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("editBook.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Edit Book");
        stage.setResizable(false);
        stage.show();

        EditBookController editBookController = loader.getController();
        int code = Integer.parseInt(String.valueOf(lbl_code.getText()));
        System.out.println(code);
        editBookController.loadBooks(code);
    }

        public void addBooksToHBox(Book book){
            this.lbl_code.setText(String.valueOf(book.getCode()));
            this.lbl_title.setText(book.getTitle());
            this.lbl_category.setText(book.getCategory().getName());
            this.lbl_author.setText(book.getAuthor().getName());
            this.lbl_published_date.setText(String.valueOf(book.getPublishedDate()));
            this.lbl_is_available.setText(String.valueOf(book.getIsAvailable()));
        }
}

