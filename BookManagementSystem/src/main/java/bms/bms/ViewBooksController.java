package bms.bms;

import bms.bms.model.entity.Book;
import bms.bms.model.service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ViewBooksController implements Initializable {

    @FXML
    private Button btn_back_click;

    @FXML
    private TableColumn<Book, String> col_author;

    @FXML
    private TableColumn<Book, String> col_cate;

    @FXML
    private TableColumn<Book, Integer> col_code;

    @FXML
    private TableColumn<Book, LocalDate> col_pubDate;

    @FXML
    private TableColumn<Book, String> col_title;

    @FXML
    private TableView<Book> tbl_data;

    @FXML
    private TextField txt_author;

    @FXML
    private TextField txt_cate;

    @FXML
    private TextField txt_title;

    @FXML
    void btn_search_click(ActionEvent event) {
        String title = txt_title.getText();
        String author = txt_author.getText();
        String cate = txt_cate.getText();

        List<Book> bookList= DatabaseService.FindAllBooks(title, author, cate);
        if(bookList.size() > 0){
            tbl_data.setItems(FXCollections.observableArrayList(bookList));
        }
        else{
            tbl_data.setItems(null);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_pubDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        col_cate.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
    }
}

