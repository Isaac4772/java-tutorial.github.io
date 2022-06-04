package lms.librarymanagement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.*;
import lms.librarymanagement.model.services.DatabaseService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditBookController extends BookItemsController implements Initializable{

    @FXML
    private Button btn_auth;

    @FXML
    private Button btn_cate;

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
    private Button btn_cancel;

    @FXML
    private Button btn_save;
    @FXML
    private TextField txt_title;

    private List<Author> authorList;
    private List<Category> categoryList;
    private int bookCode;

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

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
    void btn_del(ActionEvent event) {

    }

    @FXML
    void btn_save_on_click(ActionEvent event) throws IOException {
        Book book = new Book();
        book.setCode(getBookCode());
        book.setTitle(txt_title.getText());
        book.setPublishedDate(cbo_pub_date.getValue());

        int author_index = cbo_auth.getSelectionModel().getSelectedIndex();
        int cat_index = cbo_cate.getSelectionModel().getSelectedIndex();

        book.setAuthor(authorList.get(author_index));
        book.setCategory(categoryList.get(cat_index));

        book.setIsAvailable(rdo_avail.getSelectedToggle() == rdo_yes? 1: 0);


        System.out.println(book.getCode());
        System.out.println(book.getTitle());
        System.out.println(book.getCategory().getCategoryId()+". "+book.getCategory().getName());
        System.out.println(book.getAuthor().getAuthor_id()+". " +book.getAuthor().getName());
        System.out.println(book.getIsAvailable());
        System.out.println(book.getPublishedDate());

        if(DatabaseService.updateBook(book)) {
            Stage stage = (Stage) btn_save.getScene().getWindow();
            stage.close();
            showAlert(Alert.AlertType.INFORMATION, "Update success");

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


    public void loadBooks(int code) {
        setBookCode(code);
        Book book = DatabaseService.getBookByCode(code);
        txt_title.setText(book.getTitle());
        cbo_pub_date.setValue(book.getPublishedDate());
        cbo_auth.getSelectionModel().select(book.getAuthor().getName());
        cbo_cate.getSelectionModel().select(book.getCategory().getName());
        if(book.getIsAvailable() < 1){
            rdo_yes.setSelected(false);
            rdo_no.setSelected(true);
        }
    }
}
