package lms.librarymanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Member;
import lms.librarymanagement.model.services.DatabaseService;

import java.time.LocalDate;
import java.time.Year;

public class AddMemberController {

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_save;

    @FXML
    private TextField txt_academic_year;

    @FXML
    private TextField txt_class_year;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_roll_no;

    @FXML
    void btn_cancel_on_click() {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btn_clear_on_click() {
        txt_name.clear();
        txt_class_year.clear();
        txt_roll_no.clear();
        txt_academic_year.clear();
    }

    @FXML
    void btn_save_on_click() {
        String name = txt_name.getText();
        String rollNo = txt_roll_no.getText();
        String classYear = txt_class_year.getText();
        String academicYear = txt_academic_year.getText();

        if(name.isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Please insert name");
            return;
        }

        else if(rollNo.isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Please insert roll number");
            return;
        }

        else if(classYear.isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Please insert class year");
            return;
        }

        else if(academicYear.isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Please insert academic year");
            return;
        }

        Member member = new Member();
        member.setName(name);
        member.setRollNo(rollNo);
        member.setClassYear(classYear);
        member.setAcademicYear(Year.parse(academicYear));
        member.setCreatedAt(LocalDate.now());
        member.setExpireAt(member.getCreatedAt().plusYears(1));

        if(DatabaseService.addMember(member)){
            showAlert(Alert.AlertType.INFORMATION, "A new member is added");
            Stage stage = (Stage) btn_save.getScene().getWindow();
            stage.close();
        }
        else{
            showAlert(Alert.AlertType.ERROR, "Something went wrong");
        }
    }

    private void showAlert(Alert.AlertType type, String msg){
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.show();
    }

}
