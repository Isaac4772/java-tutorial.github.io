package bms;
	
import java.io.IOException;
import java.util.Objects;

import com.sun.tools.javac.Main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class BMS_Main extends Application {
	
	private static Stage primaryStage;
	@Override
	public void start(Stage stage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/Main.fxml")));
			Scene scene = new Scene(root);
			primaryStage = stage;
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Book Management System");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void changeScene(String view) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(BMS_Main.class.getResource(view)));
		Scene scene = new Scene(root);
		primaryStage.hide();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
