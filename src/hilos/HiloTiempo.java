package hilos;

import application.CampoJuegoController;
import modelo.Avion;

public class HiloTiempo extends Thread {

	private Avion jugador;
	private CampoJuegoController campo;
	
	public HiloTiempo (Avion jugador, CampoJuegoController campo) {
		this.jugador = jugador;
		this.campo = campo;
	}

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
