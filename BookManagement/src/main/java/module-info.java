module main.bookmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    
    exports bms;
    opens bms to javafx.fxml;
    exports bms.controller;
    opens bms.controller to javafx.fxml;
}