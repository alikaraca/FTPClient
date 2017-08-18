package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@FXML
	private ImageView image;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
			Scene scene = new Scene(root,450,350);
			primaryStage.setTitle("Anasayfa");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.setStyle("-fx-background-color: #52F3FF");
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("ftp-icon.jpg"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
