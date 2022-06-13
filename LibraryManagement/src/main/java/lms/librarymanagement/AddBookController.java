package lms.librarymanagement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Author;
import lms.librarymanagement.model.entity.Book;
import lms.librarymanagement.model.entity.Category;
import lms.librarymanagement.model.services.DatabaseService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController extends HomeController implements Initializable {

    @FXML
    private Button btn_auth;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_cate;

    @FXML
    private Button btn_save;

    @FXML
    private ComboBox<String> cbo_auth;

    @FXML
    private ComboBox<String> cbo_cate;

    @FXML
    private DatePicker cbo_pub_date;

    @FXML
    private ToggleGroup rdo_avail;

    @FXML
    private RadioButton rdo_no;

    @FXML
    private RadioButton rdo_yes;

    @FXML
    private TextField txt_title;

    private List<Author> authorList;
    private List<Category> categoryList;
    

    @FXML
    void btn_add_new(ActionEvent event) {

    }

    @FXML
    void btn_cancel_on_click(ActionEvent event) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btn_clear_on_click(ActionEvent event) {
        txt_title.setText(null);
        cbo_pub_date.setValue(null);
        cbo_auth.getSelectionModel().clearSelection();
        cbo_cate.getSelectionModel().clearSelection();
        rdo_yes.setSelected(true);
    }

    @FXML
    void btn_save_on_click(ActionEvent event) {

        String title = txt_title.getText();
        LocalDate pub_date = cbo_pub_date.getValue();
        int author_index = cbo_auth.getSelectionModel().getSelectedIndex();
        int category_index = cbo_cate.getSelectionModel().getSelectedIndex();
        int available = 1;

        if(title.isEmpty()) {
            showAlert(AlertType.WARNING,"Please enter title");
            return;
        }
        if(pub_date == null) {
            showAlert(AlertType.WARNING,"Please setup publish date");
            return;
        }
        if(author_index == -1) {
            showAlert(AlertType.WARNING,"Please choose author");
            return;
        }
        if(category_index == -1) {
            showAlert(AlertType.WARNING,"Please choose category");
            return;
        }

        Book book = new Book();
        book.setTitle(title);
        book.setPublishedDate(pub_date);
        book.setAuthor(authorList.get(author_index));
        book.setCategory(categoryList.get(category_index));
        book.setIsAvailable(available);

        System.out.println(book.getTitle());
        System.out.println(book.getCategory().getCategoryId()+". "+book.getCategory().getName());
        System.out.println(book.getAuthor().getAuthor_id()+". " +book.getAuthor().getName());
        System.out.println(book.getIsAvailable());
        System.out.println(book.getPublishedDate());

        if(DatabaseService.addNewBook(book)) {
            Stage stage = (Stage) btn_save.getScene().getWindow();
            stage.close();
            showAlert(Alert.AlertType.INFORMATION, "A new book is added");
        }else {
            showAlert(Alert.AlertType.ERROR, "Something is wrong");
        }
    }
    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setContentText(msg);
        alert.setHeaderText(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCategories();
        loadAuthors();
    }

    private void loadAuthors() {
        authorList = DatabaseService.getAllAuthors();
        List<String> names = authorList.stream()
                .map(Author::getName).toList();
        cbo_auth.setItems(FXCollections.observableArrayList(names));
    }

    private void loadCategories() {
        categoryList = DatabaseService.getAllCategories();

        List<String> names = categoryList.stream()
                .map(Category::getName).toList();
        cbo_cate.setItems(FXCollections.observableArrayList(names));
    }
}


