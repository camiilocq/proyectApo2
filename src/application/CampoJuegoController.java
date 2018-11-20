package application;
import hilos.HiloDisparo;
import hilos.HiloTiempo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
//	@FXML private Label nombre;
	
	@FXML private Circle disparo;
	@FXML private Rectangle pane;
	
	@FXML private Button pausar;
	private boolean control;
	private boolean juego;
	private HiloDisparo hDisparo;
	private HiloTiempo hTiempo;
	private Timeline animation;
	
	public void initialize() {
		
		juego = true; 
		tiempo.setText(0+"");
		hTiempo = new HiloTiempo(this);
		hDisparo = new HiloDisparo(Main.getNivel().getJugador().getDisparo(), this);
		disparo.setVisible(false);
		
		animation = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
			
			int dx = 5;
			@Override
			public void handle(ActionEvent a) {
//				hTiempo.start();
				Main.getNivel().getJugador().setPosX(Main.getNivel().getJugador().getPosX()+dx);
				jugador.setLayoutX(Main.getNivel().getJugador().getPosX());
				jugador.setLayoutY(Main.getNivel().getJugador().getPosY());
				
				if(Main.getNivel().getJugador().getPosX()>= (pane.getWidth()-jugador.getFitWidth()*0.9)) {
					Main.getNivel().getJugador().setPosX(0);
					Main.getNivel().getJugador().setPosY(Main.getNivel().getJugador().getPosY()+30);
				}
				
				if(Main.getNivel().getJugador().getDisparo().getPosY()>pane.getHeight()) {
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
							Main.getNivel().getJugador().getDisparo().setPosX(jugador.getLayoutX());
							Main.getNivel().getJugador().getDisparo().setPosY(jugador.getLayoutY());
							disparo.setLayoutX(Main.getNivel().getJugador().getDisparo().getPosX());
							disparo.setLayoutY(Main.getNivel().getJugador().getDisparo().getPosY());
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
		
	}

	public boolean isControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
	}

	public boolean isJuego() {
		return juego;
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
		juego = !juego;
		
	}
	
	public boolean verificar(){
		double x = Main.getNivel().getJugador().getPosX();
		double y = Main.getNivel().getJugador().getPosY();
		if(x==pane.getHeight()-edificio1.getFitHeight()) {
			animation.stop();
			return true;
		}else if(edificio2.getLayoutX()-x <= edificio1.getFitWidth()&&edificio2.getLayoutY()-y<=edificio2.getFitHeight()) {
			animation.stop();
			return true;
		}else if(edificio3.getLayoutX()-x <= edificio1.getFitWidth()&&edificio2.getLayoutY()-y<=edificio3.getFitHeight()) {
			animation.stop();
			return true;
		}else if(edificio4.getLayoutX()-x <= edificio1.getFitWidth()&&edificio2.getLayoutY()-y<=edificio4.getFitHeight()) {
			animation.stop();
			return true;
		}else if(edificio5.getLayoutX()-x <= edificio1.getFitWidth()&&edificio2.getLayoutY()-y<=edificio5.getFitHeight()) {
			animation.stop();
			return true;
		}else if(edificio6.getLayoutX()-x <= edificio1.getFitWidth()&&edificio2.getLayoutY()-y<=edificio6.getFitHeight()) {
			animation.stop();
			return true;
		}else if(edificio7.getLayoutX()-x <= edificio1.getFitWidth()&&edificio2.getLayoutY()-y<=edificio7.getFitHeight()) {
			animation.stop();
			return true;
		}else if(edificio8.getLayoutX()-x <= edificio1.getFitWidth()&&edificio2.getLayoutY()-y<=edificio8.getFitHeight()) {
			animation.stop();
			return true;
		}else 
			return false;
	}
		
	}
