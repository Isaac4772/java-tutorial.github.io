package lms.librarymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    public static void changeScene(String page, String title, boolean resizable) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(page));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.setResizable(resizable);
        primaryStage.show();
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}