package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modelo.Avion;
import modelo.Disparo;
import modelo.Edificio;
import modelo.Nivel;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private static Nivel nivel;
	

	/**
	 * Comentario
	 */
	
	public Main() {
		Avion av1 = new Avion(10, 0, "Jhonnier", 100,null, 0, 0);
		Avion av2 = new Avion(25, 0, "Camilo", 150,null, 0, 0);
		Avion av3 = new Avion(5, 0, "Default", 50,null, 0, 0);
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
		nivel.agregar(av1);
		nivel.agregar(av2);
		nivel.agregar(av3);
		
	}
	
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
	
	public Avion buscar(String nombre) {
		return nivel.buscar(nombre);
	}

	public static Nivel getNivel() {
		return nivel;
	}

	public static void setNivel(Nivel nivel) {
		Main.nivel = nivel;
	}

	public static void main(String[] args) {
		launch(args);
	}



}
