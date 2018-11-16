package modelo;

public class Nivel {

	private Edificio[] edificio;
	private Avion jugador;
	
	public Nivel(Edificio[] edificio, Avion jugador) {
		this.edificio = edificio;
		this.jugador = jugador;
	}
	public Edificio[] getEdificio() {
		return edificio;
	}
	public void setEdificio(Edificio[] edificio) {
		this.edificio = edificio;
	}
	public Avion getJugador() {
		return jugador;
	}
	public void setJugador(Avion jugador) {
		this.jugador = jugador;
	}
	
	
	

}
