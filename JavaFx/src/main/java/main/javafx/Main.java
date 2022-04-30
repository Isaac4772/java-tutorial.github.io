package main.javafx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage originalStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        originalStage = stage;
        stage.setTitle("Form!");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String fxml) throws IOException {
        FXMLLoader root = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(root.load());
        originalStage.hide();
        originalStage.setScene(scene);
        originalStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}