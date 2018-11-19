package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modelo.Avion;
import modelo.Disparo;
import modelo.Edificio;
import modelo.Nivel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private static Nivel nivel;
	
	public Main() {
		/*
		 * verifique que lo pueda subir
		 * 
		 * 
		 * tygyuhijioiou
		 * 
		 * 
		 */
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
				
		setNivel(new Nivel(edificios, new Avion(0, 1,"camilo", 0, new Disparo(false,43,110), 15, 26)));
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("Inicio.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("TANK WAR");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
