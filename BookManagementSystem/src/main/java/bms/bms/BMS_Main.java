package bms.bms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BMS_Main extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BMS_Main.class.getResource("BMS_main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage = stage;
        stage.setTitle("Book Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String view) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BMS_Main.class.getResource(view));
        Scene newScene = new Scene(fxmlLoader.load());
        primaryStage.hide();
        primaryStage.setScene(newScene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}