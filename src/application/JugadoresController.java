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
/**
 * @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase JugadoresController que controla el archivo Jugadores.fxml
 */
public class JugadoresController {
	/**
	 * area de texto que simboliza el listado de jugadores
	 */
	@FXML private TextArea lisJugadores;
	/**
	 * textField que simboliza el nombre del jugador
	 */
	@FXML private TextField criterio;
	/**
	 * boton que permite buscar un jugado en especifico
	 */
	@FXML private Button buscar;
	/**
	 * check que permite ordenar los jugadores por nombre
	 */
	@FXML private CheckBox nombre;
	/**
	 * check que permite ordenar los jugadores por puntuacion
	 */
	@FXML private CheckBox puntuacion;
	/**
	 * boton que permite mostrar el primer jugador
	 */
	@FXML private Button primero;
	/**
	 * boton que permite mostrar el ultimo jugador
	 */
	@FXML private Button ultimo;
	/**
	 * boton que permite mostrar el ultimo jugador
	 */
	@FXML private Button totalJugadores;
	/**
	 * boton que permite volver al inicio del juego
	 */
	@FXML private Button volver;
	
	/**
	 * metodo que permite inicializar los atributos de la clase
	 */
	public void initialize() {
		lisJugadores.setEditable(false);
		loadJugadores();
	}
	
	/**
	 *metodo que permite mostrar los jugadores en el campo de tetxo
	 */
	public void mostrar() {
		Avion act = Main.getNivel().getJugador();
		int num = 1;
		while(act != null) {
			lisJugadores.setText(lisJugadores.getText()+num+". "+act.getNombre()+"\n");
			num++;
			act = act.getDer();
		}act = Main.getNivel().getJugador();
		while(act.getIzq()!=null){
			lisJugadores.setText(lisJugadores.getText()+num+". "+act.getNombre()+"\n");
			num++;
			act = act.getIzq();
		}
	}
	/**
	 *metodo que buscar un jugador en especifico
	 */
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
	/**
	 *metodo que mostar el primer jugador
	 */
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
		
	/**
	 *metodo que mostrar el ultimo jugador
	 */
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
		
	/**
	 *metodo que permite hacer persistir los aviones
	 */
	public void loadJugadores() {
		Main.getNivel().leer();
	}
	
	/**
	 *metodo que mostrar el numero de jugadores
	 */
	public void totalJugadores() {
		Alert alert = new Alert(AlertType.INFORMATION, "HAY "+Main.getNivel().getNumAviones()+" AVION(ES)", ButtonType.OK);
		alert.showAndWait();	
	}
	
	/**
	 *metodo que permite volver al inicio del juego
	 */
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
