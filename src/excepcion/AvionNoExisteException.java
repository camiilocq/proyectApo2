package excepcion;

/**
  * @author Juan Camilo Castillo
 * @author Jhonnier Isaza Gonzalez
 * Esta es la clase AvionNoExisteException que simboliza la excepcion cuando un avion a buscar no existe
 */
public class AvionNoExisteException extends Exception {

	/**
	 * atributo que simboliza el mensaje a mostrar
	 */
	private String mensaje;

	/**
	 * este es el contructos de AvionNoExisteException
	 * @param mensaje - mensaje que simboliza el mensaje a mostrar
	 */
	public AvionNoExisteException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
	
	
}
