package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import modelo.Avion;
import modelo.Disparo;

public class DificultadController {

	@FXML private Button facil;
	@FXML private Button intermedio;
	@FXML private Button dificil;
	@FXML private Button volver;
	@FXML private Button jugar;
	@FXML private TextArea contexto;
	
	public void intialize() {
	}
	
	public void facil() {
		String texto = "";
		contexto.setText(texto);
		try {
			File file = new File("archivos/facil.txt");
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			while((texto = bf.readLine())!=null) {
				contexto.setText(contexto.getText()+texto+"\n");
			}
			bf.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void intermedio() {
		String texto = "";
		contexto.setText(texto);
		try {
			File file = new File("archivos/intermedio.txt");
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			while((texto = bf.readLine())!=null) {
				contexto.setText(contexto.getText()+texto+"\n");
			}
			bf.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dificil() {
		String texto = "";
		contexto.setText(texto);
		try {
			File file = new File("archivos/dificil.txt");
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			while((texto = bf.readLine())!=null) {
				contexto.setText(contexto.getText()+texto+"\n");
			}
			bf.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void volver(Event event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
			Scene scene = new Scene(root);
			Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			windows.setScene(scene);
			windows.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void jugar(Event event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Mensaje");
		dialog.setHeaderText("¡A JUGAR!");
		dialog.setContentText("Por favor ingresa tu nombre: ");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			int velocidad = 0;
			int puntuacion = 0;
			Avion jugador = new Avion(velocidad, 1, result.get(), puntuacion, new Disparo(false,43,110), 65 ,26);
			Main.getNivel().setJugador(jugador);
		}
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("CampoJuego.fxml"));
			Scene scene = new Scene(root);
			Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			windows.setScene(scene);
			windows.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
