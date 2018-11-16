package modelo;

public class Disparo extends Ente {

	private boolean activo;
	
	public Disparo(boolean activo, double posX, double posY) {
		super(posX, posY);
		this.activo = activo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public boolean isMovible() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
