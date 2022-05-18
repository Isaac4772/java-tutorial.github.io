package bms.bms;

import bms.bms.model.entity.Author;
import bms.bms.model.entity.Book;
import bms.bms.model.entity.Category;
import bms.bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditBookController implements Initializable {

    @FXML
    private ComboBox<String> cbo_author;

    @FXML
    private ComboBox<String> cbo_cate;

    @FXML
    private TextField txt_code;

    @FXML
    private DatePicker txt_pub;

    @FXML
    private TextField txt_title;

    @FXML
    void btn_cancel_click(ActionEvent event) {
        txt_code.setText(null);
        txt_title.setText(null);
        txt_pub.setValue(null);
        cbo_cate.getSelectionModel().clearSelection();
        cbo_author.getSelectionModel().clearSelection();
    }

    @FXML
    void btn_search_click(ActionEvent event) {
        int code = Integer.parseInt(txt_code.getText());
        Book searchedBook = DatabaseService.findBookByCode(code);
        if(searchedBook != null){
            txt_title.setText(searchedBook.getTitle());
            txt_pub.setValue(searchedBook.getPublishedDate());
            cbo_author.getSelectionModel().select(searchedBook.getAuthorName());
            cbo_cate.getSelectionModel().select(searchedBook.getCategoryName());
        }
        else{
            showAlert(Alert.AlertType.ERROR,"No Book with Code " + code);
        }
    }

    @FXML
    void btn_back_click(ActionEvent event) throws IOException {
        BMS_Main.changeScene("Books.fxml");
    }
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void btn_update_click(ActionEvent event) {
        Book book = new Book();
        book.setCode(Integer.parseInt(txt_code.getText()));
        book.setTitle(txt_title.getText());
        book.setPublishedDate(txt_pub.getValue());

        int author_index = cbo_author.getSelectionModel().getSelectedIndex();
        int cat_index = cbo_cate.getSelectionModel().getSelectedIndex();

        book.setAuthor(authorList.get(author_index));
        book.setCategory(categoryList.get(cat_index));

        if(DatabaseService.updateBook(book)){
            showAlert(Alert.AlertType.INFORMATION, "Update Success");
            txt_code.setText(null);
            txt_title.setText(null);
            txt_pub.setValue(null);
            cbo_cate.getSelectionModel().clearSelection();
            cbo_author.getSelectionModel().clearSelection();
        }
        else{
            showAlert(Alert.AlertType.ERROR, "Something went wrong :(");
        }

    }

    private List<Author> authorList;
    private List<Category> categoryList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAuthors();
        loadCategories();
    }

    private void loadCategories() {
        categoryList = DatabaseService.getAllCategories();

        List<String> names = categoryList.stream().map(Category::getName).toList();

        cbo_cate.setItems(FXCollections.observableArrayList(names));

    }

    private void loadAuthors() {
        authorList = DatabaseService.getAllAuthors();

        List<String> names = authorList.stream().map(Author::getName).toList();

        cbo_author.setItems(FXCollections.observableArrayList(names));
    }
}


