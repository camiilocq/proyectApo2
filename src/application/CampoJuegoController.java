package application;
import java.io.IOException;

import excepcion.AvionNoExisteException;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Avion;

/**
 *  @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase CampoJuegoController que controla el archivo CampoJuego.fxml
 */
public class CampoJuegoController {
	/**
     * Imagen que controla el avion en el juego
     */
	@FXML private ImageView  jugador;
	/**
     * Imagen que controla el edificio #1
     */
	@FXML private ImageView edificio1;
	/**
     * Imagen que controla el edificio #2
     */
	@FXML private ImageView edificio2;
	/**
     * Imagen que controla el edificio #3
     */
	@FXML private ImageView edificio3;
	/**
     * Imagen que controla el edificio #4
     */
	@FXML private ImageView edificio4;
	/**
     * Imagen que controla el edificio #5
     */
	@FXML private ImageView edificio5;
	/**
     * Imagen que controla el edificio #6
     */
	@FXML private ImageView edificio6;
	/**
     * Imagen que controla el edificio #7
     */
	@FXML private ImageView edificio7;
	/**
     * Imagen que controla el edificio #8
     */
	@FXML private ImageView edificio8;
	
	/**
     * Imagen que controla el misil que dispara el avion
     */
	@FXML private ImageView misil;
	/**
     * Imagen que controla el fondo del juego
     */
	@FXML private ImageView fondo;
	
	/**
     * Etiqueta que simboliza el tiempo del jugador
     */
	@FXML private Label tiempo;
	/**
     * Etiqueta que simboliza la puntuacion del jugador
     */
	@FXML private Label puntuacion;
	/**
     * Etiqueta que simboliza el nombre del jugador
     */
	@FXML private Label nomJugador;
	/**
     * Rectangulo que simboliza el tamaño del campo de juego
     */
	@FXML private Rectangle pane;
	/**
     * Boton que permite pausar el juego
     */
	@FXML private Button pausar;
	/**
     * Boton que permite reiniciar el juego
     */
	@FXML private Button reiniciar;
	/**
     * Boton que permite guardar el juego
     */
	@FXML private Button guardar;
	
	/**
     *Hilo que permite la actualizacion del tiempo
     */
	private HiloTiempo hTiempo;
	/**
     *Hilo que permite la actualizacion del puntaje
     */
	private HiloPuntuacion hPuntuacion;
	/**
     * linea de tiempo que permite la animacion del juego
     */
	private Timeline animation;
	/**
     *avion que sera el jugador del juego
     */
	private Avion juga;
	/**
     * metodo que permite la inicializacion de los atributos
     */
	public void initialize() {
		juga = Main.getNivel().getJugador().darMenor();
		//======================================================================================
		//						INICIO EL HILO
		//======================================================================================
		hTiempo = new HiloTiempo(juga,this);
		hPuntuacion = new HiloPuntuacion(juga, this);
		//======================================================================================
		//						OTRAS VARIABLES
		//======================================================================================
		
		nomJugador.setText(juga.getNombre().toUpperCase());
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
					animation.stop();

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
	
	/**
	 * Avion!=null y Disparo!=null
     * metodo que permite verificar si el misil impacto en un edificio
     * @return retorno - boleano que simboliza si impacto o no el misil
     */
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

	/**
	 * animacion!=null
     * metodo que permite verificar si la animacion esta corrriendo
     * @return retorno - boleano que simboliza si la animacion esta o no corriendo
     */
	public boolean isJuego() {
		boolean retorno = false;
		if(animation.getStatus().equals(Animation.Status.RUNNING)) {
			retorno = true;
			return retorno;
		}else{
			return retorno;
		}
	}
	
	/**
     * metodo que dar la etiqueta con el tiempo
     * @return tiempo - etiqueta que simboliza el tiempo
     */
	public Label getTiempo() {
		return tiempo;
	}
	
	/**
     * metodo que permite modificar la etiqueta con el tiempo
     * @param tiempo -  etiqueta que simboliza el nuevo tiempo 
     */
	public void setTiempo(Label tiempo) {
		this.tiempo = tiempo;
	}
	
	/**
     * metodo que permite pausar la animacion o reanudarla
     */
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
	
	/**
     * metodo que permite reiniciar la animacion desde el principio
     */
	public void reiniciar() {
		juga.setPosX(0);
		juga.setPosY(0);
		juga.setPuntuacion(0);
		juga.setTiempo(0);
	}
	
	/**
     * metodo que permite verificar si el avon a chocado contra un edificio o no
     * @return control - boleano que simboliza si choco o no el avion
     */
	public boolean verificar(){
		boolean control = true;
		double disY = jugador.getLayoutY()-10;
		double disX = jugador.getLayoutX()-jugador.getFitWidth();
		if(disX<61) {
			if(disY>edificio1.getLayoutY()-jugador.getFitHeight() && edificio1.isVisible()) {
				return control;
			}
		}else if(disX<107 && disX >=61) {
			if(disY>edificio2.getLayoutY()-jugador.getFitHeight() && edificio2.isVisible()) {
				return control;
			}
		}else if(disX<183 && disX >=107) {
			if(disY>edificio3.getLayoutY()-jugador.getFitHeight() && edificio3.isVisible()) {
				return control;
			}
		}else if(disX<219 && disX >=183) {
			if(disY>edificio4.getLayoutY()-jugador.getFitHeight() && edificio4.isVisible()) {
				return control;
			}
		}else if(disX<273 && disX >=219) {
			if(disY>edificio5.getLayoutY()-jugador.getFitHeight() && edificio5.isVisible()) {
				return control;
			}
		}else if(disX<340 && disX >=273) {
			if(disY>edificio6.getLayoutY()-jugador.getFitHeight() && edificio6.isVisible()) {
				return control;
			}
		}else if(disX<422 && disX >=340) {
			if(disY>edificio7.getLayoutY()-jugador.getFitHeight() && edificio7.isVisible()) {
				return control;
			}
		}else if(disX<516 && disX >=422) {
			if(disY>edificio8.getLayoutY()-jugador.getFitHeight()+5 && edificio8.isVisible()) {
				return control;
			}
		}
		return !control;
	}
	
	/**
     * metodo que permite guardar el jugador y llevar al usuario a la ventana con el ranking
     */
	public void guardar(Event event){
		animation.stop();
		try {
			Main.getNivel().buscar(juga.getNombre()).setPuntuacion(Integer.parseInt(puntuacion.getText()));
		} catch (NumberFormatException | NullPointerException | AvionNoExisteException e1) {
			e1.printStackTrace();
		}
		try {
			Main.getNivel().buscar(juga.getNombre()).setTiempo(Integer.parseInt(tiempo.getText()));
		} catch (NumberFormatException | NullPointerException | AvionNoExisteException e1) {
			e1.printStackTrace();
		}
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
