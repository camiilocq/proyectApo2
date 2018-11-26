package excepcion;
	/**
	 * @author Juan Camilo Castillo
	 * @author Jhonnier Isaza Gonzalez
	 * Esta es la clase AvionRepetidoException que simboliza la excepcion cuando un avion a agregar ya existe
	 */
public class AvionRepetidoException extends Exception {

	/**
	 * atributo que simboliza el mensaje a mostrar
	 */
	private String mensaje;

	/**
	 * este es el contructos de AvionRepetidoException
	 * @param mensaje - mensaje que simboliza el mensaje a mostrar
	 */
	public AvionRepetidoException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
	
	
}
