module lms.librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens lms.librarymanagement to javafx.fxml;
    exports lms.librarymanagement;

    exports lms.librarymanagement.model.entity;
}