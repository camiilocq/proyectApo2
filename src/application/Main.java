package application;
	
import java.io.IOException;

import excepcion.AvionNoExisteException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modelo.Avion;
import modelo.Edificio;
import modelo.Nivel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
  * @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase Main que permite la comuncacion entre el modelo y la vista
 */
public class Main extends Application {
	
	/**
	 * nivel que permite la obtencion de un nivel
	 */
	private static Nivel nivel;
	

	/**
	 * Este es el contructor de la clase Main donde se inicializan los atributos de nivel
	 */
	
	public Main() {
		
		Edificio[] edificios = new Edificio[10];
		edificios[1] = new Edificio(-14,392);
		edificios[2] = new Edificio(43,300);
		edificios[3] = new Edificio(114,373);
		edificios[4] = new Edificio(171,464);
		edificios[5] = new Edificio(228,391);
		edificios[6] = new Edificio(285,335);
		edificios[7] = new Edificio(342,430);
		edificios[8] = new Edificio(387,373);
		edificios[9] = new Edificio(432,300);
				
		setNivel(new Nivel(edificios,null));
		
		
	}
	
	/**
	 * metodo que permite arrancar la vista del juego
	 */
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("Inicio.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("AIR WAR");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite buscar un jugador del modelo llamado en la vista 
	 * @param nombre - cadena de texto que simboliza el nombre del jugador
	 * @return avion buscado
	 */
	public Avion buscar(String nombre) {
		try {
			return nivel.buscar(nombre);
		} catch (NullPointerException | AvionNoExisteException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * metodo que da un nivel del modelo
	 * @return nivel que simboliza el nivel del modelo
	 */
	public static Nivel getNivel() {
		return nivel;
	}

	/**
	 * metodo que cambia el nivel del modelo
	 * @param nivel - nivel que simboliza el nuevo nivel
	 */
	public static void setNivel(Nivel nivel) {
		Main.nivel = nivel;
	}

	/**
	 * Hilo principal del Juego
	 * @param args - vector de tamaño fijo d tipo String
	 */
	public static void main(String[] args) {
		launch(args);
	}



}
