package hilos;

import modelo.Avion;

public class HiloJugador implements Runnable {

	private Avion jugador;
	//INTERFAZ DONDE SE MOSTRARA EL MOVIMIENTO
	
	public HiloJugador(Avion jugador) {
		this.jugador = jugador;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
