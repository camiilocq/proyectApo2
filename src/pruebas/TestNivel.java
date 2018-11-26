package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import excepcion.AvionNoExisteException;
import modelo.Avion;
import modelo.Nivel;
/**
 * Esta es la clase TestNivel que hace los test a la clase Nivel
 */
class TestNivel {
	/**
	 * atributo que permite obtener el nivel del modelo
	 */
	private Nivel nivel;

	/**
	 * metodo que permite crea un nivel con un avion por nombre "Camilo" y edificio null;
	 */
	public void escenario1() {
		Avion a = new Avion(10, 0, "Camilo", 100, null, 10, 10);
		nivel = new Nivel(null, null);
		nivel.agregar(a);
	}
	/**
	 * metodo que permite crea un nivel con avion null y edificio null
	 */
	public void escenario2() {
		nivel = new Nivel(null, null);
	}
	
	/**
	 * metodo que permite hacer test al metodo buscar del nivel
	 */
	@Test
	public void BuscarTest() {
		escenario1();
		Avion esperado = null;
		try {
			esperado = nivel.buscar("Camilo");
		} catch (NullPointerException | AvionNoExisteException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(esperado.getNombre(), "Camilo");
	}
	/**
	 * metodo que permite hacer test al metodo agregar del nivel
	 */
	@Test
	public void AgregarTest() {
		escenario2();
		nivel.agregar(new Avion(10, 0, "Jhonnier", 100, null, 10, 10));
		try {
			assertEquals(nivel.getJugador(), nivel.buscar("Jhonnier"));
		} catch (NullPointerException | AvionNoExisteException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * metodo que permite hacer test al metodo eliminar del nivel
	 */
	@Test
	public void eliminarTest() {
		escenario1();
		nivel.eliminarProgramador("Camilo");
		assertEquals(nivel.getJugador(), null);
	}
	
}
