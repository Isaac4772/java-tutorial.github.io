package bms.bms;

import bms.bms.model.entity.Author;
import bms.bms.model.entity.Book;
import bms.bms.model.entity.Category;
import bms.bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddBookController implements Initializable {
    @FXML
    private ComboBox<String> cbo_author;

    @FXML
    private ComboBox<String> cbo_category;

    @FXML
    private TableColumn<Book, String> col_author;

    @FXML
    private TableColumn<Book, String> col_cate;

    @FXML
    private TableColumn<Book, Integer> col_code;

    @FXML
    private TableColumn<Book, LocalDate> col_pblish_date;

    @FXML
    private TableColumn<Book, String> col_title;

    @FXML
    private TableView<Book> tbl_data;

    @FXML
    private DatePicker txt_pblish_date;

    @FXML
    private TextField txt_title;
    private List<Category> categoryList;
    private List<Author> authorList;

    @FXML
    void btn_clear_click(ActionEvent event) {

    }

    @FXML
    void btn_back_click(ActionEvent event) throws IOException {
        BMS_Main.changeScene("Books.fxml");
    }

    @FXML
    void btn_save_click(ActionEvent event) {
        String title = txt_title.getText();
        LocalDate pub_date = txt_pblish_date.getValue();
        int author_index = cbo_author.getSelectionModel().getSelectedIndex();
        int cate_index = cbo_category.getSelectionModel().getSelectedIndex();

        if(title.isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Please Enter title");
            return;
        }
        if(pub_date == null){
            showAlert(Alert.AlertType.WARNING,"Please setup published date");
            return;
        }
        if(author_index == -1){
            showAlert(Alert.AlertType.WARNING, "Please choose author");
            return;
        }
        if(cate_index == -1){
            showAlert(Alert.AlertType.WARNING, "Please choose category");
            return;
        }

        //saveBook
        Book book = new Book();
        book.setTitle(title);
        book.setPublishedDate(pub_date);
        book.setCategory(categoryList.get(cate_index));
        book.setAuthor(authorList.get(author_index));

        if(DatabaseService.saveBook(book)){

        }
        else{
            showAlert(Alert.AlertType.ERROR, "Something is wrong, please try again");
        }
    }

    private void showAlert(Alert.AlertType type, String body) {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(body);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAuthors();
        loadCategories();
        loadBooks();
    }

    private void loadBooks() {
        col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_pblish_date.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        col_cate.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        List<Book> bookList = DatabaseService.getAllBooks();

        tbl_data.setItems(FXCollections.observableArrayList(bookList));
    }

    private void loadCategories() {
        categoryList = DatabaseService.getAllCategories();

        List<String> names = categoryList.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
        cbo_category.setItems(FXCollections.observableArrayList(names));

    }

    private void loadAuthors() {

        authorList = DatabaseService.getAllAuthors();

        List<String> names = authorList.stream()
                .map(Author::getName)
                .collect(Collectors.toList());

        cbo_author.setItems(FXCollections.observableArrayList(names));

    }
}
