package excepcion;

public class AvionNoExisteException extends Exception {

	private String mensaje;

	public AvionNoExisteException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
	
	
}
