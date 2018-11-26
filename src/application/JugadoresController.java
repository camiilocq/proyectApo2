package application;

import java.io.IOException;

import excepcion.AvionNoExisteException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.Avion;

public class JugadoresController {

	@FXML private TextArea lisJugadores;
	@FXML private TextField criterio;
	@FXML private Button buscar;
	@FXML private CheckBox nombre;
	@FXML private CheckBox puntuacion;
	@FXML private Button primero;
	@FXML private Button ultimo;
	@FXML private Button totalJugadores;
	@FXML private Button volver;
	
	public void initialize() {
		lisJugadores.setEditable(false);
		loadJugadores();
	}
	
	public void buscar() {
		String dificultad = "";
		if(criterio.getText()!=null) {
			try {
			Avion a = Main.getNivel().buscar(criterio.getText());
			if(a.getVelocidad()==35) {
				dificultad = "FACIL";
		}else if(a.getVelocidad()  == 30) {
			dificultad = "INTERMEDIO";
		}else if(a.getVelocidad() == 25) {
			dificultad = "DIFICIL";
		}
		Alert alert = new Alert(AlertType.INFORMATION, "Jugador: "+a.getNombre().toUpperCase()+" con una puntuacion de: "+a.getPuntuacion()+
				" y un tiempo de: "+a.getTiempo()+" segundos en la dificultad de: "+dificultad, ButtonType.OK);
		alert.showAndWait();	
			}catch(AvionNoExisteException e) {
				Alert alert = new Alert(AlertType.INFORMATION, e.getMessage(), ButtonType.OK, ButtonType.CANCEL);
				alert.showAndWait();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION, "INGRESE EL NOMBRE DEL JUGADOR", ButtonType.OK, ButtonType.CANCEL);
			alert.showAndWait();	
		}
	}
	
	public void primero() {
		loadJugadores();
		String dificultad = "default";
		Avion a = Main.getNivel().getJugador().darMayor();
	
		if(a.getVelocidad()==35) {
			dificultad = "FACIL";
		}else if(a.getVelocidad()  == 30) {
			dificultad = "INTERMEDIO";
		}else if(a.getVelocidad() == 25) {
			dificultad = "DIFICIL";
		}
		Alert alert = new Alert(AlertType.INFORMATION, "Jugador: "+a.getNombre().toUpperCase()+" con una puntuacion de: "+a.getPuntuacion()+
				" y un tiempo de: "+a.getTiempo()+" segundos en la dificultad de: "+dificultad, ButtonType.OK);
		alert.showAndWait();	
		}
		
	
	public void ultimo() {
		loadJugadores();
		String dificultad = "default";
		Avion a = Main.getNivel().getJugador().darMenor();
	
		if(a.getVelocidad()==35) {
			dificultad = "FACIL";
		}else if(a.getVelocidad()  == 30) {
			dificultad = "INTERMEDIO";
		}else if(a.getVelocidad() == 25) {
			dificultad = "DIFICIL";
		}
		Alert alert = new Alert(AlertType.INFORMATION, "Jugador: "+a.getNombre().toUpperCase()+" con una puntuacion de: "+a.getPuntuacion()+
				" y un tiempo de: "+a.getTiempo()+" segundos en la dificultad de: "+dificultad, ButtonType.OK);
		alert.showAndWait();	
		}
		
	public void loadJugadores() {
		Main.getNivel().leer();
	}
	public void totalJugadores() {
		Alert alert = new Alert(AlertType.INFORMATION, "HAY "+Main.getNivel().getNumAviones()+" AVION(ES)", ButtonType.OK);
		alert.showAndWait();	
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
	
	
}
