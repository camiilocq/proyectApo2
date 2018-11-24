package hilos;

import application.CampoJuegoController;
import modelo.Avion;

public class HiloPuntuacion extends Thread {

	private Avion jugador;
	private CampoJuegoController campo;
	
	public HiloPuntuacion(Avion jugador, CampoJuegoController campo) {
		this.jugador = jugador;
		this.campo = campo;
	}

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
