package lms.librarymanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Librarian;
import lms.librarymanagement.model.services.DatabaseService;

import java.time.LocalDate;

public class AddLibrarianController {

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_save;

    @FXML
    private TextField txt_nrcno;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_phone;

    @FXML
    private TextField txt_username;

    @FXML
    void btn_cancel_on_click() {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btn_clear_on_click() {
        txt_username.clear();
        txt_password.clear();
        txt_nrcno.clear();
        txt_phone.clear();
    }

    @FXML
    void btn_save_on_click() {
        Librarian librarian = new Librarian();
        String username = txt_username.getText();
        String password = txt_password.getText();
        String nrcNo = txt_nrcno.getText();
        String phone = txt_phone.getText();
        LocalDate creationDate = LocalDate.now();

        if(username.isEmpty())
            showAlert(Alert.AlertType.WARNING, "Please enter username");
        else if(password.isEmpty())
            showAlert(Alert.AlertType.WARNING, "Please enter password");
        else if(nrcNo.isEmpty())
            showAlert(Alert.AlertType.WARNING, "Please enter Nrc Number");
        else if(phone.isEmpty())
            showAlert(Alert.AlertType.WARNING, "Please enter phone number");

        librarian.setUsername(username);
        librarian.setPassword(password);
        librarian.setNrcNo(nrcNo);
        librarian.setPhone(phone);
        librarian.setCreatedAt(creationDate);

        if(DatabaseService.addLibrarian(librarian)){
            showAlert(Alert.AlertType.INFORMATION, "A new librarian added");
            Stage stage = (Stage) btn_save.getScene().getWindow();
            stage.close();
        }
    }

    private void showAlert(Alert.AlertType type, String header) {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setHeaderText(header);
        alert.show();
    }
}
