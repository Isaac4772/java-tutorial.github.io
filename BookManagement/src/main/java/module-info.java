module main.bookmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.bookmanagement to javafx.fxml;
    exports main.bookmanagement;
}