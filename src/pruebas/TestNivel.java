package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Avion;
import modelo.Nivel;

class TestNivel {
	
	private Nivel nivel;

	public void escenario1() {
		Avion a = new Avion(10, 0, "Camilo", 100, null, 10, 10);
		Avion b = new Avion(10, 0, "Jhonnier", 100, null, 10, 10);
		nivel = new Nivel(null, null);
		nivel.agregar(a);
		nivel.agregar(b);
	}
	
	public void escenario2() {
		nivel = new Nivel(null, null);
	}
	
	@Test
	public void BuscarTest() {
		escenario1();
		Avion esperado = nivel.buscar("Camilo");
		assertEquals(esperado.getNombre(), "Camilo");
	}

	@Test
	public void AgregarTest() {
		escenario2();
		nivel.agregar(new Avion(10, 0, "Jhonnier", 100, null, 10, 10));
		assertEquals(nivel.getJugador(), nivel.buscar("Jhonnier"));
	}
}
