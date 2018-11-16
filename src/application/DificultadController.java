package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
