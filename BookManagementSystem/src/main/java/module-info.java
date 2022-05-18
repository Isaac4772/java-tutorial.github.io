module bms.bms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens bms.bms to javafx.fxml;
    exports bms.bms;
    opens bms.bms.model.entity to javafx.fxml;
    exports bms.bms.model.entity;
}