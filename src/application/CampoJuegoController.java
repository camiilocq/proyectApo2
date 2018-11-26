package application;
import java.io.IOException;

import hilos.HiloDisparo;
import hilos.HiloPuntuacion;
import hilos.HiloTiempo;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Avion;

public class CampoJuegoController {
	
	@FXML private ImageView  jugador;
	@FXML private ImageView edificio1;
	@FXML private ImageView edificio2;
	@FXML private ImageView edificio3;
	@FXML private ImageView edificio4;
	@FXML private ImageView edificio5;
	@FXML private ImageView edificio6;
	@FXML private ImageView edificio7;
	@FXML private ImageView edificio8;
	
	@FXML
	private ImageView fondo;
	
	
	@FXML private Label tiempo;
	@FXML private Label puntuacion;
	@FXML private Label nomJugador;
	
	@FXML private Circle disparo;
	@FXML private Rectangle pane;
	
	@FXML private Button pausar;
	@FXML private Button reiniciar;
	@FXML private Button guardar;
	
	private boolean control;
	private boolean juego;
	private HiloDisparo hDisparo;
	private HiloTiempo hTiempo;
	private HiloPuntuacion hPuntuacion;
	private Timeline animation;
	
	private Avion juga;
	
	public void initialize() {
		juga = Main.getNivel().getJugador();
		tiempo.setText("000");
		nomJugador.setText(juga.getNombre().toUpperCase());
		juego = true; 
		hTiempo = new HiloTiempo(juga,this);
		hPuntuacion = new HiloPuntuacion(juga, this);
		hDisparo = new HiloDisparo(juga.getDisparo(), this);
		disparo.setVisible(false);
		
		
		animation = new Timeline(new KeyFrame(Duration.millis(juga.getVelocidad()), new EventHandler<ActionEvent>() {
			
			int dx = 5;
			@Override
			public void handle(ActionEvent a) {
				tiempo.setText(juga.getTiempo()+"");
				puntuacion.setText(juga.getPuntuacion()+"");
				juga.setPosX(juga.getPosX()+dx);
				jugador.setLayoutX(juga.getPosX());
				jugador.setLayoutY(juga.getPosY());
				
				if(juga.getPosX()>= (pane.getWidth()-jugador.getFitWidth()*0.9)) {
					juga.setPosX(0);
					juga.setPosY(juga.getPosY()+30);
				}
				
				if(juga.getDisparo().getPosY()>pane.getHeight()) {
					control = false;
				}
				
				if(verificar()) {
					Alert mensaje = new Alert(AlertType.INFORMATION, "chocaste", ButtonType.OK);
					mensaje.show();
				}
				fondo.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						if(control == false) {
							juga.getDisparo().setPosX(jugador.getLayoutX()+50);
							juga.getDisparo().setPosY(jugador.getLayoutY()+70);
							disparo.setLayoutX(juga.getDisparo().getPosX());
							disparo.setLayoutY(juga.getDisparo().getPosY());
							disparo.setVisible(true);
							hDisparo.start();
						}else {
							System.out.println("No se puede");
						}
					}
				});
				
			}
			
		}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		hTiempo.start();
		hPuntuacion.start();
	}
	
	public boolean isControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
	}

	public boolean isJuego() {
		if(animation.getStatus().equals(Animation.Status.RUNNING)) {
			return true;
		}else{
			return false;
		}
	}

	public void setJuego(boolean juego) {
		this.juego = juego;
	}

	public Label getTiempo() {
		return tiempo;
	}

	public void setTiempo(Label tiempo) {
		this.tiempo = tiempo;
	}

	public Circle getDisparo() {
		return disparo;
	}

	public void setDisparo(Circle disparo) {
		this.disparo = disparo;
	}
	
	public void pausar() {
	if(animation.getStatus().equals(Animation.Status.RUNNING)) {
		animation.stop();
		pausar.setText("INICIAR");
	}else{
		animation.play();
		pausar.setText("PAUSAR");
		hTiempo = new HiloTiempo(juga, this);
		hPuntuacion = new HiloPuntuacion(juga, this);
		hTiempo.start();
		hPuntuacion.start();
	}
	}
	
	public void reiniciar() {
		juga.setPosX(0);
		juga.setPosY(0);
		juga.setPuntuacion(0);
		juga.setTiempo(0);
	}
	
	public boolean verificar(){
		double x = juga.getPosX();
		double y = juga.getPosY();
//	if(y-edificio1.getLayoutY()>=0) {
//		return true;
//	}
//	else {
//		return false;
//	}
		return false;
	}
	
	public void guardar(Event event){
		Main.getNivel().guardar(juga);
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
	
}
