package modelo;

public class Edificio extends Ente {
	
	public final static char LARGO = 'L';
	public final static char CORTO = 'C';
	private char tipo;
	
	public Edificio(double posX, double posY) {
		super(posX, posY);
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

		

}
