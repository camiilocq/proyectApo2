package application;
import java.io.IOException;

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
import modelo.Disparo;

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
	
	private HiloTiempo hTiempo;
	private HiloPuntuacion hPuntuacion;
	private Timeline animation;
	
	private Avion juga;
	
	public void initialize() {
		juga = Main.getNivel().getJugador();
		//======================================================================================
		//						INICIO EL HILO
		//======================================================================================
		hTiempo = new HiloTiempo(juga,this);
		hPuntuacion = new HiloPuntuacion(juga, this);
		//======================================================================================
		//						OTRAS VARIABLES
		//======================================================================================
		
		nomJugador.setText(juga.getNombre().toUpperCase());
		juego = true; 
		disparo.setVisible(false);
		juga.getDisparo().setPosX(juga.getPosX());
		juga.getDisparo().setPosY(juga.getPosY());
		
		animation = new Timeline(new KeyFrame(Duration.millis(juga.getVelocidad()), new EventHandler<ActionEvent>() {
			
			int dx = 5;
			@Override
			public void handle(ActionEvent a) {
				//======================================================================================
				//						controlo puntaje y tiempo
				//======================================================================================
				tiempo.setText(juga.getTiempo()+"");
				puntuacion.setText(juga.getPuntuacion()+"");
				//======================================================================================
				//						controlo posicion del avion
				//======================================================================================
				juga.setPosX(juga.getPosX()+dx);
				jugador.setLayoutX(juga.getPosX());
				jugador.setLayoutY(juga.getPosY());
		
				if(juga.getPosX()>= (pane.getWidth()-jugador.getFitWidth()*0.9)) {
					juga.setPosX(0);
					juga.setPosY(juga.getPosY()+30);
				}
				
				if(verificar()) {
					Alert mensaje = new Alert(AlertType.INFORMATION, "Tu avion choco, tienes un puntaje de: "+puntuacion.getText()+" y un tiempo de: "+tiempo.getText()+" seg.", ButtonType.OK);
					animation.stop();
					mensaje.show();
//					guardar()
				}
				
				//======================================================================================
				//						controlo el disparo
				//======================================================================================
				
				disparo.setLayoutX(juga.getDisparo().getPosX());
				disparo.setLayoutY(juga.getDisparo().getPosY());
				
				if(juga.getDisparo().getPosY()>pane.getHeight()*0.8) {
					juga.getDisparo().setActivo(false);
					disparo.setVisible(false);
				}
				if(juga.getDisparo().isActivo()) {
					juga.getDisparo().setPosY(juga.getDisparo().getPosY()+4);
				}
				if(impacto()) {
					disparo.setVisible(false);
					juga.getDisparo().setPosX(juga.getPosX());
					juga.getDisparo().setPosY(juga.getPosY());
					juga.getDisparo().setActivo(false);
				}
				fondo.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						if(juga.getDisparo().isActivo()==false) {
						juga.getDisparo().setActivo(true);
						disparo.setVisible(true);
						}else {
							System.out.println("aun no");
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
	
	public boolean impacto() {
		boolean retorno = false;
		double posX = juga.getDisparo().getPosX();
		double posY = juga.getDisparo().getPosY();
		if(edificio1.getLayoutX()<=posX && posX<=edificio1.getLayoutX()+edificio1.getFitWidth()) {
			if((edificio1.getLayoutY()+edificio1.getFitHeight())-posY<=10) {
			edificio1.setVisible(false);
			retorno = true;
			}
		}else if(edificio2.getLayoutX()<=posX && posX<=edificio2.getLayoutX()+edificio2.getFitWidth()) {
			if((edificio2.getLayoutY()+edificio2.getFitHeight())-posY<=10) {
			edificio2.setVisible(false);
			retorno = true;
			}
		}else if(edificio3.getLayoutX()<=posX && posX<=edificio3.getLayoutX()+edificio3.getFitWidth()) {
			if((edificio3.getLayoutY()+edificio3.getFitHeight())-posY<=10) {
			edificio3.setVisible(false);
			retorno = true;
			}
		}else if(edificio4.getLayoutX()<=posX && posX<=edificio4.getLayoutX()+edificio4.getFitWidth()) {
			if((edificio4.getLayoutY()+edificio4.getFitHeight())-posY<=10) {
			edificio4.setVisible(false);
			retorno = true;
			}
		}else if(edificio5.getLayoutX()<=posX && posX<=edificio5.getLayoutX()+edificio5.getFitWidth()) {
			if((edificio5.getLayoutY()+edificio5.getFitHeight())-posY<=10) {
			edificio5.setVisible(false);
			retorno = true;
			}
		}else if(edificio6.getLayoutX()<=posX && posX<=edificio6.getLayoutX()+edificio6.getFitWidth()) {
			if((edificio6.getLayoutY()+edificio6.getFitHeight())-posY<=10) {
			edificio6.setVisible(false);
			retorno = true;
			}
		}else if(edificio7.getLayoutX()<=posX && posX<=edificio7.getLayoutX()+edificio7.getFitWidth()) {
			if((edificio7.getLayoutY()+edificio7.getFitHeight())-posY<=10) {
			edificio7.setVisible(false);
			retorno = true;
			}
		}else if(edificio8.getLayoutX()<=posX && posX<=edificio8.getLayoutX()+edificio8.getFitWidth()) {
			if((edificio8.getLayoutY()+edificio8.getFitHeight())-posY<=10) {
			edificio8.setVisible(false);
			retorno = true;
			}
		}
		return retorno;
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
