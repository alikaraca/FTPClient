package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.util.ArrayList;
import org.apache.commons.net.ftp.FTPClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class arayuz1Controller {
	@FXML
	private TextField txt1;
	@FXML
	private TextField txt2;
	@FXML
	private TextField txt3;
	@FXML
	private TextField txt4;
	@FXML
	private Button btn_sunucu;
	@FXML
	private Button geri;
	@FXML
	private Button btn_listele;
	@FXML
	private ListView<String> lst;
	@FXML
	private TextField txt5;
	@FXML
	private TextField txt6;
	@FXML
	private Button btn_gozat;
	@FXML
	private Button btn_yukle;
	@FXML
	private Label lbl_giris;
	@FXML
	private Label lbl_yukle;
	@FXML
	private Label lbl_baglantý;
	String klasor;
	String server,prt;
	int port;
	FTPClient ftp=new FTPClient();
	FileChooser fileChooser=new FileChooser();
	DirectoryChooser dr=new DirectoryChooser();
	ArrayList<String> b=new ArrayList<>();
	@FXML
	public void sunucu_Click(ActionEvent event) {
		try {
			server=txt1.getText();
			prt=Integer.toString(port);
			prt=txt5.getText();
			port=Integer.parseInt(prt);
			ftp.connect(server);
			boolean baglantiDurumu=ftp.isConnected();
			if(baglantiDurumu==true) {
				lbl_baglantý.setText("Baðlantý Kuruldu");
			}else {
				lbl_baglantý.setText("Baðlantý Kurulamadý");
			}
			String kullaniciadi=txt3.getText();
			String parola=txt4.getText();
			ftp.enterLocalActiveMode();
			boolean durum1=ftp.login(kullaniciadi, parola);
			if(durum1==true) {
				lbl_giris.setText("Giriþ Yapýldý");
			}else {
				lbl_giris.setText("Giriþ Yapýlamadý");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	public void listele_click(ActionEvent event) throws IOException {
		File dosyalar1=new File(txt5.getText());
		File[] dosyalar = dosyalar1.listFiles();
		for (File file : dosyalar) {
			b.add(file.getName());
			System.out.println(file.getName());
		}
		ObservableList<String> dosya=FXCollections.observableArrayList(b);
		lst.setItems(dosya);
		lst.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				txt6.setText(lst.getSelectionModel().getSelectedItem());	
			}
			
		});
	}
	@FXML
	public void Click_gozat(ActionEvent event) {
		btn_gozat.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Stage stage=new Stage();
				File dizin=dr.showDialog(stage);
				String path=dizin.getAbsolutePath();
                txt5.setText(path);
                stage.close();
			}
		});
	}
	@FXML
	public void Click_yukle(ActionEvent event) throws IOException {
		String kayitDizini=txt1.getText();
		String dosyaAdi=txt2.getText();
		File file=new File(kayitDizini);
		OutputStream outputStream=new BufferedOutputStream(new FileOutputStream(file));
		boolean yukleme=ftp.retrieveFile(dosyaAdi, outputStream);
		if(yukleme==true) {
			lbl_yukle.setText("Yükleme Baþarýlý");
		}else {
			lbl_yukle.setText("Yükleme Baþarýsýz");
		}
		
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
