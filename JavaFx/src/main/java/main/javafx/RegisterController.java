package main.javafx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_password;
    @FXML
    private DatePicker birthday_picker;
    @FXML
    private ComboBox <String> cho_city;
    @FXML
    private ToggleGroup gender;
    @FXML
    private CheckBox chk_java, chk_python, chk_mysql;
    @FXML
    private TextArea edu_textarea;

    @FXML
    public void btn_login_click() throws IOException {
        Main.changeScene("Sample.fxml");
    }

    public void btn_register_click(ActionEvent actionEvent) {
        String name = txt_name.getText();
        String password = txt_password.getText();
        LocalDate birthday = birthday_picker.getValue();
        RadioButton selectedRadio = (RadioButton) gender.getSelectedToggle();

        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        System.out.println("Birthday: " + birthday);
        System.out.println("Gender: " + selectedRadio.getText());
        String skillsets = "";

        int index = cho_city.getSelectionModel().getSelectedIndex();
        if(index == -1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Message");
            alert.setContentText("Please choose city");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        String city = cho_city.getSelectionModel().getSelectedItem();
        System.out.println("City: " + city);
        if(chk_java.isSelected()){
            skillsets += chk_java.getText() + ",";
        }
        if(chk_python.isSelected()){
            skillsets += chk_python.getText() + ",";
        }
        if(chk_mysql.isSelected()){
            skillsets += chk_mysql.getText() + ",";
        }

        System.out.println("Skillsets: " + skillsets.substring(0, skillsets.lastIndexOf(",")));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> cities = List.of("Yangon", "Mandalay", "Monywa");

        cho_city.setItems(FXCollections.observableArrayList(cities));
    }
}
