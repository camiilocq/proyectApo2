package application;

import java.io.IOException;

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
	}
	
	public void buscar() {
		Alert alert = new Alert(AlertType.INFORMATION, "AQUI DEBE APARECER LA INFORMACION DEL JUGADOR", ButtonType.OK, ButtonType.CANCEL);
		alert.showAndWait();	
	}
	
	public void primero() {
		Alert alert = new Alert(AlertType.INFORMATION, "AQUI DEBE APARECER LA INFORMACION DEL PRIMER JUGADOR", ButtonType.OK, ButtonType.CANCEL);
		alert.showAndWait();	
	}
	
	public void ultimo() {
		Alert alert = new Alert(AlertType.INFORMATION, "AQUI DEBE APARECER LA INFORMACION DE LOS ULTIMOS JUGADOR", ButtonType.OK, ButtonType.CANCEL);
		alert.showAndWait();	
	}
	
	public void totalJugadores() {
		Alert alert = new Alert(AlertType.INFORMATION, "AQUI DEBE APARECER EL NUMERO DE JUGADORES QUE HAY", ButtonType.OK, ButtonType.CANCEL);
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
