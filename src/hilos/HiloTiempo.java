package hilos;

import application.CampoJuegoController;
import modelo.Avion;
/**
  * @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase HiloTiempo que permite manipular el tiempo del Avion
 */
public class HiloTiempo extends Thread {
	/**
	 * atributo jugador que simboliza el avion del modelo
	 */
	private Avion jugador;
	/**
	 * atributo campo que simboliza el campo de la interfaz
	 */
	private CampoJuegoController campo;
	/**
	 * constructor de la clase HilTiempo
	 * @param jugador - Avion que simboliza el jugador del modelo
	 * @param campo - CampoJuegoController que simboliza el campo de la interfaz
	 */
	public HiloTiempo (Avion jugador, CampoJuegoController campo) {
		this.jugador = jugador;
		this.campo = campo;
	}
	/**
	 * metodo que permite manipular el tiempo del avion
	 */
	@Override
	public void run() {
		while(campo.isJuego()){
			jugador.setTiempo(jugador.getTiempo()+1);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
