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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import modelo.Avion;
import modelo.Disparo;
/**
  * @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase DificultadController que controla el archivo Dificultad.fxml
 */
public class DificultadController {

	/**
     * boton que permite escoger el nivel facil
     */
	@FXML private Button facil;
	/**
     * boton que permite escoger el nivel intermedio
     */
	@FXML private Button intermedio;
	/**
     * boton que permite escoger el nivel dificil
     */
	@FXML private Button dificil;
	/**
     * boton que permite volver al inicio del juego
     */
	@FXML private Button volver;
	/**
     * boton que permite llevar la jugador al campo de juego
     */
	@FXML private Button jugar;
	/**
     * area de texto que contendra un resumen de cada nivel
     */
	@FXML private TextArea contexto;
	/**
     * char que simbolia el nivel que se escogio
     */
	private char tipo = 'N';
	
	/**
     * metodo que permite inicializar los atributos
     */
	public void intialize() {
		
	}
	/**
     * metodo que permite mostrar el resumen del nivel facil
     */
	public void facil() {
		String texto = "";
		tipo = 'F';
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
	/**
     * metodo que permite mostrar el resumen del nivel intermemdio
     */
	public void intermedio() {
		String texto = "";
		tipo = 'I';
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
	/**
     * metodo que permite mostrar el resumen del nivel dificil
     */
	public void dificil() {
		String texto = "";
		tipo = 'D';
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
	/**
     * metodo que permite volver al inicio del juego
     * @param event - evento que da la ventana donde se encuentra
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
	/**
     * metodo que permite llevar al jugador al campo de juego
     * @param event - evento que da la ventana donde se encuentra
     */
	public void jugar(Event event) {
		if(tipo == 'N') {
			Alert alert = new Alert(AlertType.WARNING, "NO HA ESCOGIDO NINGUNA DIFICULTAD", ButtonType.OK);
			alert.showAndWait();
		}else {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Mensaje");
			dialog.setHeaderText("¡A JUGAR!");
			dialog.setContentText("Por favor ingresa tu nombre: ");
			
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				int velocidad = 0;
				if(tipo=='F') 
					velocidad = 35;
				else if(tipo == 'I') 
					velocidad = 30;
				else if(tipo == 'D') 
					velocidad = 25;
				
					int puntuacion =0;
					Avion jugador = new Avion(1, velocidad, result.get(), puntuacion, new Disparo(false,10,10), 10 ,10);
					Main.getNivel().agregar(jugador);	
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
	}
}
