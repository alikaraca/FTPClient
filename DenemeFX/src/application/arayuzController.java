package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class arayuzController {
	@FXML
	private Button btn;
	@FXML
	private Button geri;
	@FXML
	private Button btn_g;
	@FXML
	private Button btn2;
	@FXML
	private Button gozat;
	@FXML
	private TextField txt;
	@FXML
	private TextField txt1;
	@FXML
	private TextField txt2;
	@FXML
	private TextField txt3;
	@FXML
	private TextField txt4;
	@FXML
	private TextField txt5;
	@FXML
	private ListView<String> lst1;
	@FXML
	private Label lbl;
	@FXML
	private Label lbl_yukleme;
	@FXML
	private Label giris;
	@FXML
	private Label durum;
	FTPClient ftp=new FTPClient();
	ArrayList<String> b=new ArrayList<>();
	FileChooser fileChooser=new FileChooser();
	DirectoryChooser dr=new DirectoryChooser();
	String server;
	int port;
	String prt;
	public void btn_Giris(ActionEvent event) throws SocketException, IOException {
		try {
			server=txt.getText();
			prt=Integer.toString(port);
			prt=txt5.getText();
			port=Integer.parseInt(prt);
			ftp.connect(server);
			boolean baglantiDurumu=ftp.isConnected();
			if(baglantiDurumu==true) {
				durum.setText("Baðlantý Kuruldu");
			}else {
				durum.setText("Baðlantý Kurulamadý");
			}
			String kullaniciadi=txt3.getText();
			String parola=txt4.getText();
			ftp.enterLocalActiveMode();
			boolean durum1=ftp.login(kullaniciadi, parola);
			if(durum1==true) {
				giris.setText("Giriþ Yapýldý");
			}else {
				giris.setText("Giriþ Yapýlamadý");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	public void btn_click(ActionEvent event) throws SocketException, IOException {
		String kayitDizini=txt1.getText();
		String dosyaAdi=txt2.getText();
		File file=new File(kayitDizini);
		OutputStream outputStream=new BufferedOutputStream(new FileOutputStream(file));
		boolean yukleme=ftp.retrieveFile(dosyaAdi, outputStream);
		if(yukleme==true) {
			lbl_yukleme.setText("Yükleme Baþarýlý");
		}else {
			lbl_yukleme.setText("Yükleme Baþarýsýz");
		}
		
	}
	public void gozat_Click(ActionEvent event) {
		gozat.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Stage stage=new Stage();
				File dizin=fileChooser.showSaveDialog(stage);
				String path=dizin.getAbsolutePath();
                txt1.setText(path);
                stage.close();
			}
		});
	}
	public void listele(ActionEvent event) throws IOException {
		FTPFile[] dosyalar = ftp.listFiles("/");
		for (FTPFile file : dosyalar) {
			b.add(file.getName());
			System.out.println(file.getName());
		}
		ObservableList<String> dosya=FXCollections.observableArrayList(b);
		lst1.setItems(dosya);
		lst1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				txt2.setText(lst1.getSelectionModel().getSelectedItem());	
			}
			
		});
	}
	public void btn_GeriDon(ActionEvent event) throws IOException {
		    Stage stage = (Stage) geri.getScene().getWindow();
		    AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
	        Scene scene = new Scene(root);
	        stage.setTitle("Anasayfa");
	        root.setStyle("-fx-background-color: #52F3FF");
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        stage.setScene(scene);
	        System.out.println("Anasayfa Açýldý");
	}
	
}
