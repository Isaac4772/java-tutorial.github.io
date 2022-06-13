package lms.librarymanagement;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Book;
import lms.librarymanagement.model.entity.BorrowedBook;
import lms.librarymanagement.model.entity.Member;
import lms.librarymanagement.model.services.DatabaseService;

public class AddBorrowedBookController implements Initializable {
	
	@FXML
    private ListView<String> book_data;
	
	@FXML
	private ListView<String> member_data;

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_cate;

	@FXML
	private Button btn_save;


	private static List<Book> bookList;
	private static List<Member> memberList;

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
		member_data.getSelectionModel().clearSelection();
		book_data.getSelectionModel().clearSelection();
	}

	@FXML
	void btn_save_on_click(ActionEvent event) {
		BorrowedBook Bbook = new BorrowedBook();
		int bookIndex = book_data.getSelectionModel().getSelectedIndex();
		int memberIndex = member_data.getSelectionModel().getSelectedIndex();
		
		Bbook.setBookId(bookList.get(bookIndex).getCode());
		Bbook.setCardId(memberList.get(memberIndex).getCardId());
		if(DatabaseService.addNewBorrowedBook(Bbook)){
			Stage stage = (Stage) btn_save.getScene().getWindow();
            stage.close();
            showAlert(Alert.AlertType.INFORMATION, "A new borrowed book is added");
		}
		else {
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
		memberList.forEach(s->System.out.println(s.getCardId()));
		for (Member member : memberList) {
			memberIdAndName.add(String.valueOf(member.getCardId()) + ". " + member.getName());
		}
		member_data.setItems(FXCollections.observableArrayList(memberIdAndName));

	}

	private void loadBooks() {
		bookList = DatabaseService.getAvailableBooks();
		List<String> bookIdAndName = new ArrayList<>();
		for (Book book : bookList) {
			bookIdAndName.add(String.valueOf(book.getCode()) + ". " + book.getTitle());
		}
		book_data.setItems(FXCollections.observableArrayList(bookIdAndName));
	}

}
