package lms.librarymanagement;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Book;
import lms.librarymanagement.model.entity.BorrowedBook;
import lms.librarymanagement.model.entity.Member;
import lms.librarymanagement.model.services.DatabaseService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBorrowedBookController implements Initializable {

    private static List<Book> bookList;
    private static List<Member> memberList;
    @FXML
    private ListView<String> book_data;
    @FXML
    private ListView<String> member_data;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_save;
    @FXML
    private TextField txt_search_boooks_to_borrow;
    @FXML
    private TextField txt_search_member;

    public AddBorrowedBookController() {
    }

    @FXML
    void btn_cancel_on_click() {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btn_clear_on_click() {
        member_data.getSelectionModel().clearSelection();
        book_data.getSelectionModel().clearSelection();
    }

    @FXML
    void btn_save_on_click() {
        BorrowedBook bBook = new BorrowedBook();
        int bookIndex = book_data.getSelectionModel().getSelectedIndex();
        int memberIndex = member_data.getSelectionModel().getSelectedIndex();

        bBook.setBookId(bookList.get(bookIndex).getCode());
        bBook.setCardId(memberList.get(memberIndex).getCardId());
        bBook.setBorrowDate(LocalDate.now());
        if (DatabaseService.addNewBorrowedBook(bBook)) {
            Stage stage = (Stage) btn_save.getScene().getWindow();
            stage.close();
            showAlert(Alert.AlertType.INFORMATION, "A new borrowed book is added");
        } else {
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
    public void initialize(URL location, ResourceBundle resources) {
        loadBooks();
        loadMembers();
    }

    private void loadMembers() {
        memberList = DatabaseService.getAllMembers();
        List<String> memberIdAndName = new ArrayList<>();
        memberList.forEach(s -> System.out.println(s.getCardId()));
        for (Member member : memberList) {
            memberIdAndName.add(member.getCardId() + ". " + member.getName());
        }
        member_data.setItems(FXCollections.observableArrayList(memberIdAndName));

    }

    private void loadBooks() {
        bookList = DatabaseService.getAvailableBooks();
        List<String> bookIdAndName = new ArrayList<>();
        for (Book book : bookList) {
            bookIdAndName.add(book.getCode() + ". " + book.getTitle());
        }
        book_data.setItems(FXCollections.observableArrayList(bookIdAndName));
    }

    @FXML
    void searchBooks(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            List<Book> bookResults = new ArrayList<>();
            String search = txt_search_boooks_to_borrow.getText().toLowerCase();
            System.out.println(search);
            for (Book book : bookList) {
                String code = String.valueOf(book.getCode());
                String title = String.valueOf(book.getTitle()).toLowerCase();

                if (code.contains(search)) {
                    if (!bookResults.contains(book))
                        bookResults.add(book);
                }
                if (title.contains(search)) {
                    if (!bookResults.contains(book))
                        bookResults.add(book);
                }
            }
            if (bookResults.size() > 0) { // check if searched results exist
                List<String> bookIdAndName = new ArrayList<>();
                for (Book book : bookResults) {
                    bookIdAndName.add(book.getCode() + ". " + book.getTitle());
                }
                book_data.setItems(FXCollections.observableArrayList(bookIdAndName));
            }
        } else
            loadBooks();
    }

    @FXML
    void searchMembers(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            List<Member> memberResults = new ArrayList<>();
            String search = txt_search_member.getText().toLowerCase();
            System.out.println(search);
            for (Member member : memberList) {
                String cardId = String.valueOf(member.getCardId());
                String memberName = member.getName().toLowerCase();

                if (cardId.contains(search)) {
                    if (!memberResults.contains(member))
                        memberResults.add(member);
                }
                if (memberName.contains(search)) {
                    if (!memberResults.contains(member))
                        memberResults.add(member);
                }
            }
            if (memberResults.size() > 0) { // check if searched results exist
                List<String> memberIdAndName = new ArrayList<>();
                memberList.forEach(s -> System.out.println(s.getCardId()));
                for (Member member : memberList) {
                    memberIdAndName.add(member.getCardId() + ". " + member.getName());
                }
                member_data.setItems(FXCollections.observableArrayList(memberIdAndName));
            }
        } else
            loadMembers();
    }

}
