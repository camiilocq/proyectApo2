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
/**
  * @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase InicioController que controla el archivo Inicio.fxml
 */
public class InicioController {
	/**
	 * boton que permite empezar el juego
	 */
	@FXML private Button jugar;
	/**
	 * boton que permite mostrar el listado de jugadores
	 */
	@FXML private Button jugadore;
	/**
	 * boton que permite mostrar un mensaje de ayude
	 */
	@FXML private Button ayuda;
	
	/**
	 * metodo que permite inicializar los atributos
	 */
	public void initialize() {
		
	}
	/**
	 * metodo que permite empezar el juego
	 * @param event - evento que da la ventana donde se abrira el juego
	 */
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
	/**
	 * metodo que permite mostrar los jugadores
	 * @param event - evento que da la ventana donde se abrira el listado de los jugadores
	 */
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
	/**
	 * metodo que permite mostrar la ayuda
	 * @param event - evento que da la ventana donde se abrira el listado de la ayuda
	 */
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
