package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class anasayfaController implements Initializable {
	@FXML
	private Button btn_Down;
	@FXML
	private Button btn_Up;
	@FXML
	private ImageView image;
	private Stage stage;

	public void start() {
		Image image1 = new Image(Main.class.getResourceAsStream("ftp.jpg"));
		image.setImage(image1);
		Image image2=new Image(Main.class.getResourceAsStream("ftp-icon.jpg"));
		stage.getIcons().add(image2);
	}

	@FXML
	public void click_Download(ActionEvent event) throws IOException {
		stage = (Stage) btn_Down.getScene().getWindow();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("arayuz.fxml"));
		Scene scene = new Scene(root);
		root.setStyle("-fx-background-color: #52F3FF");
		stage.setTitle("FTPDownload");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		System.out.println("FTPDownload Açýldý");
	}

	// Event Listener on Button[#btn_Up].onAction
	@FXML
	public void click_Upload(ActionEvent event) throws IOException {
		stage = (Stage) btn_Down.getScene().getWindow();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("arayuz1.fxml"));
		Scene scene = new Scene(root);
		root.setStyle("-fx-background-color: #52F3FF");
		stage.setTitle("FTPUpload");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		System.out.println("FTPUpload Açýldý");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File file = new File("src/ftp.jpg");
		Image image1 = new Image(file.toURI().toString());
		image.setImage(image1);
	}
}
