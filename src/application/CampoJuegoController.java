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
	
	@FXML private ImageView misil;
	
	@FXML
	private ImageView fondo;
	
	
	@FXML private Label tiempo;
	@FXML private Label puntuacion;
	@FXML private Label nomJugador;
	
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
		misil.setVisible(false);

		
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
					mensaje.setHeaderText(juga.getNombre().toUpperCase());
					animation.stop();
					Main.getNivel().guardar();
					mensaje.show();

					/*
					 * falta por modificar
					 */
//					guardar()
					/*
					 * solo este metodo
					 */
				}
				
				//======================================================================================
				//						controlo el misil
				//======================================================================================
				
				
				if(juga.getDisparo().getPosY()>fondo.getFitHeight()) {
					juga.getDisparo().setActivo(false);
					misil.setVisible(false);
				}
				if(juga.getDisparo().isActivo()) {
					misil.setVisible(true);
					juga.getDisparo().setPosY(juga.getDisparo().getPosY()+4);
					misil.setLayoutY(juga.getDisparo().getPosY());
				}else {
					misil.setVisible(false);
				}
				if(impacto()) {
					misil.setVisible(false);
					juga.getDisparo().setPosX(juga.getPosX());
					juga.getDisparo().setPosY(juga.getPosY());
					juga.getDisparo().setActivo(false);
				}
				fondo.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						misil.setLayoutX(juga.getDisparo().getPosX());
						juga.getDisparo().setActivo(!juga.getDisparo().isActivo());
						
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
		double disY = juga.getDisparo().getPosY()+misil.getFitWidth();
		double disX = juga.getDisparo().getPosX();
		if(disX<61) {
			if(disY>edificio1.getLayoutY()) {
				edificio1.setVisible(false);
				return true;
			}
		}else if(disX<107 && disX >=61) {
			if(disY>edificio2.getLayoutY()) {
				edificio2.setVisible(false);
				return true;
			}
		}else if(disX<183 && disX >=107) {
			if(disY>edificio3.getLayoutY()) {
				edificio3.setVisible(false);
				return true;
			}
		}else if(disX<219 && disX >=183) {
			if(disY>edificio4.getLayoutY()) {
				edificio4.setVisible(false);
				return true;
			}
		}else if(disX<273 && disX >=219) {
			if(disY>edificio5.getLayoutY()) {
				edificio5.setVisible(false);
				return true;
			}
		}else if(disX<340 && disX >=273) {
			if(disY>edificio6.getLayoutY()) {
				edificio6.setVisible(false);
				return true;
			}
		}else if(disX<422 && disX >=340) {
			if(disY>edificio7.getLayoutY()) {
				edificio7.setVisible(false);
				return true;
			}
		}else if(disX<516 && disX >=422) {
			if(disY>edificio8.getLayoutY()) {
				edificio8.setVisible(false);
				return true;
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
		double disY = jugador.getLayoutY()-10;
		double disX = jugador.getLayoutX()-jugador.getFitWidth();
		if(disX<61) {
			if(disY>edificio1.getLayoutY()-jugador.getFitHeight() && edificio1.isVisible()) {
				return true;
			}
		}else if(disX<107 && disX >=61) {
			if(disY>edificio2.getLayoutY()-jugador.getFitHeight() && edificio2.isVisible()) {
				return true;
			}
		}else if(disX<183 && disX >=107) {
			if(disY>edificio3.getLayoutY()-jugador.getFitHeight() && edificio3.isVisible()) {
				return true;
			}
		}else if(disX<219 && disX >=183) {
			if(disY>edificio4.getLayoutY()-jugador.getFitHeight() && edificio4.isVisible()) {
				return true;
			}
		}else if(disX<273 && disX >=219) {
			if(disY>edificio5.getLayoutY()-jugador.getFitHeight() && edificio5.isVisible()) {
				return true;
			}
		}else if(disX<340 && disX >=273) {
			if(disY>edificio6.getLayoutY()-jugador.getFitHeight() && edificio6.isVisible()) {
				return true;
			}
		}else if(disX<422 && disX >=340) {
			if(disY>edificio7.getLayoutY()-jugador.getFitHeight() && edificio7.isVisible()) {
				return true;
			}
		}else if(disX<516 && disX >=422) {
			if(disY>edificio8.getLayoutY()-jugador.getFitHeight()+5 && edificio8.isVisible()) {
				return true;
			}
		}
		return false;
	}
	
	public void guardar(Event event){
		Main.getNivel().buscar(juga.getNombre()).setPuntuacion(Integer.parseInt(puntuacion.getText()));
		Main.getNivel().buscar(juga.getNombre()).setTiempo(Integer.parseInt(tiempo.getText()));
		Main.getNivel().guardar();
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
