package hilos;

import application.CampoJuegoController;
import modelo.Avion;

public class HiloTiempo extends Thread {

	private CampoJuegoController campo;
	
	public HiloTiempo (CampoJuegoController campo) {
		this.campo = campo;
	}

	@Override
	public void run() {
		while(campo.isJuego()){
			 int tiempo = Integer.parseInt(campo.getTiempo().getText());
		campo.getTiempo().setText(Integer.toString(tiempo+1));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
