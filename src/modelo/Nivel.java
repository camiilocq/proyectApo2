package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import excepcion.AvionNoExisteException;
import excepcion.AvionRepetidoException;

public class Nivel {

	private Edificio[] edificio;
	private Avion jugador;
	private int numAviones;
	
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
	
	public void buscarAvion(String nombre) {
		
	}
	
	public void guardar() {
		try {
		File file = new File("archivos/jugadores.data");
		FileOutputStream bs= new FileOutputStream(file);
		ObjectOutputStream os = new ObjectOutputStream (bs);
		os.writeObject(jugador);
		os.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Avion leerConNombre(String nombre)throws NullPointerException, AvionNoExisteException{
		Avion a = null;
		try {
			File file = new File("archivos/jugadores.data");
			FileInputStream bs= new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream (bs);
			a = (Avion) os.readObject();
		}catch(ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if(a!=null && a.getNombre().compareToIgnoreCase(nombre)==0) {
			return a;
		}else {
			return buscar(a.getNombre());
		}
		
	}
	
	public void leer() {
		try {
			File file = new File("archivos/jugadores.data");
			FileInputStream bs= new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream (bs);
			jugador = (Avion) os.readObject();
			os.close();
			bs.close();
		}catch(ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Avion buscar(String nombre)throws NullPointerException, AvionNoExisteException {
		if(jugador.getNombre().compareToIgnoreCase(nombre)==0) {
			return jugador;
		}else {
			return jugador.buscar(nombre);
		}
	}
	
	public void agregar(Avion a) {
		if(jugador == null) {
			jugador = a;
			setNumAviones(getNumAviones()+1);
		}else {
			try {
				jugador.insertar(a);
				setNumAviones(getNumAviones()+1);
			} catch (AvionRepetidoException e) {
				e.printStackTrace();
			}
		}
	}
	public int getNumAviones() {
		return numAviones;
	}
	public void setNumAviones(int numAviones) {
		this.numAviones = numAviones;
	}

	

}
