package lms.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.librarymanagement.model.entity.Book;
import lms.librarymanagement.model.services.DatabaseService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

	@FXML
	private HBox itemC;

	@FXML
	private Label lbl_code;
	@FXML
	private Label lbl_title;

	@FXML
	private VBox pnItems = null;
	@FXML
	private Button btnOverview;

	@FXML
	private Button btnOrders;

	@FXML
	private Button btnCustomers;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnPackages;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnSignout;

	@FXML
	private Pane pnlCustomer;

	@FXML
	private Pane pnlOrders;

	@FXML
	private Pane pnlOverview;

	@FXML
	private Pane pnlMenus;

	@FXML
	private Button btn_add_new;

	BookItemsController bookItemsController;

	private static List<Book> bookList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadBooks();
		loadNodes();

	}

	@FXML
	void btn_add_new_on_click(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("addBook.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Add Book");
		stage.setResizable(false);
		stage.show();
	}

	public void loadNodes() {
		Node[] nodes = new Node[bookList.size()];
		for (int i = 0; i < nodes.length; i++) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("bookItems.fxml"));

			try {
				final int j = i;
				nodes[i] = loader.load();
				bookItemsController = loader.getController();
				bookItemsController.addBooksToHBox(bookList.get(i));

				// give the items some effect
				nodes[i].setOnMouseEntered(event -> {
					nodes[j].setStyle(
							"-fx-background-color : #A5BECC; -fx-border-radius: 25; -fx-background-radius: 25;");
				});
				nodes[i].setOnMouseExited(event -> {
					nodes[j].setStyle(
							"-fx-background-color :  #DBDFFD; -fx-border-radius: 25; -fx-background-radius: 25;");
				});
				pnItems.getChildren().add(nodes[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(pnItems.getChildren());
		bookList = new ArrayList<>();
	}

	private void loadBooks() {
		bookList = DatabaseService.getAllBooks();
		bookList.forEach(s -> System.out.println(s.getCode()));
	}

	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnCustomers) {
			pnlCustomer.toFront();
		}
		if (actionEvent.getSource() == btnMenus) {
			pnlMenus.toFront();
		}
		if (actionEvent.getSource() == btnOverview) {
			reload();
			pnlOverview.toFront();
		}
		if (actionEvent.getSource() == btnOrders) {
			pnlOrders.toFront();
		}
	}

	public void reload() {
		loadBooks();
		pnItems.getChildren().clear();
		loadNodes();
	}
}
