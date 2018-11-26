package excepcion;

public class AvionRepetidoException extends Exception {

	private String mensaje;

	public AvionRepetidoException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
	
	
}
