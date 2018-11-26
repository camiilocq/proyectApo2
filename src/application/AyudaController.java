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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * Este es la clase AyudaController que controla el archivo Ayuda.fxml
 */
public class AyudaController {
	/**
     * Boton que permite volver al inicio del juego
     */
	@FXML private Button volver;
	/**
     * campo de texto que muestra el mensaje de ayuda
     */
	@FXML private TextArea ayuda;
	
	/**
     *metodo que inicializa los atributos de la clase
     */
	public void initialize() {
		String texto = "";
		try {
			File file = new File("archivos/AyudaFile.txt");
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			while((texto = bf.readLine())!=null) {
				ayuda.setText(ayuda.getText()+texto+"\n");
			}
			bf.close();
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
     * Metodo que permite volver al inicio del juego
     * @param event - evento que permite obtener la ventana
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
