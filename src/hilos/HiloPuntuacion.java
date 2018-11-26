package hilos;

import application.CampoJuegoController;
import modelo.Avion;

/**
  * @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase HiloPuntuacion que permite manipular la puntuacion del Avion
 */
public class HiloPuntuacion extends Thread {
	/**
	 * atributo jugador que simboliza el avion del modelo
	 */
	private Avion jugador;
	/**
	 * atributo campo que simboliza el campo de la interfaz
	 */
	private CampoJuegoController campo;
	
	/**
	 * constructor de la clase HiloPuntuacion
	 * @param jugador - Avion que simboliza el jugador del modelo
	 * @param campo - CampoJuegoController que simboliza el campo de la interfaz
	 */
	public HiloPuntuacion(Avion jugador, CampoJuegoController campo) {
		this.jugador = jugador;
		this.campo = campo;
	}

	/**
	 * metodo que permite manipular la puntuacion del avion
	 */
	@Override
	public void run() {
		while(campo.isJuego()) {
			jugador.setPuntuacion(jugador.getPuntuacion()+2);
			try {
				Thread.sleep(200);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
