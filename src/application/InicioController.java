package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {

	@FXML private Button jugar;
	@FXML private Button jugadore;
	@FXML private Button ayuda;
	
	public void initialize() {
		
	}
	
	public void jugar(Event event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Dificultad.fxml"));
			Scene scene = new Scene(root);
			Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			windows.setScene(scene);
			windows.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void jugadores(Event event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Jugadores.fxml"));
			Scene scene = new Scene(root);
			Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			windows.setScene(scene);
			windows.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Ayuda(Event event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Ayuda.fxml"));
			Scene scene = new Scene(root);
			Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			windows.setScene(scene);
			windows.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
