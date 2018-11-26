package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import excepcion.AvionRepetidoException;

public class Avion extends Ente {
	
	private int puntuacion;
	private Avion der;
	private Avion izq;
	private int tiempo;
	
	private Avion next;
	
	private double velocidad;
	
	private Disparo disparo;
	
	private String nombre;
	
	
	public Avion(int tiempo, double velocidad,String nombre,int puntuacion,Disparo disparo,double posX, double posY) {
		super(posX,posY);
		this.tiempo = tiempo;
		this.disparo = disparo;
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.velocidad = velocidad;
		der = izq = null;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public Disparo getDisparo() {
		return disparo;
	}

	public void setDisparo(Disparo disparo) {
		this.disparo = disparo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	
	public Avion getDer() {
		return der;
	}

	public void setDer(Avion der) {
		this.der = der;
	}

	public Avion getIzq() {
		return izq;
	}

	public void setIzq(Avion izq) {
		this.izq = izq;
	}
	
	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public Avion buscar(String nombre) {
		if(nombre.compareToIgnoreCase(nombre)==0) {
			return this;
		}else if(nombre.compareToIgnoreCase(nombre)>0) {
			return (izq ==null)?null:izq.buscar(nombre);
		}else {
				return (der == null)?null:der.buscar(nombre);
		}
	}
	
	public int compareTo(String name) {
		int valor = 0;
		if(this.nombre.compareTo(name) < 0) {
			valor = -1;
		} else if(this.nombre.compareTo(name) > 0) {
			valor = 1;
		}
		return valor;
	}

	public Avion getNext() {
		return next;
	}

	public void setNext(Avion next) {
		this.next = next;
	}
	
	public void inOrden(ArrayList<Avion> acumulado) {
		if (this.izq != null) {
			izq.inOrden(acumulado);
		}
		acumulado.add(this);
		if (this.der != null) {
			der.inOrden(acumulado);
		}

	}
	
	 public int compareTo( Object o )
	    {
	        Avion otro = ( Avion )o;
	        return nombre.compareToIgnoreCase( otro.nombre );
	    }
	
	 public void insertar( Avion nuevo ) throws AvionRepetidoException{
	    	if(compareTo(nuevo)==0) {
	    		throw new AvionRepetidoException("EL AVION YA ESTA REGISTRADO");
	    	}
	    	if(compareTo(nuevo)>0) {
	    		if(izq ==null) {
	    			izq = nuevo;
	    		}else {
	    			izq.insertar(nuevo);
	    		}
	    	}else {
	    		if(der ==null) {
	    			der = nuevo;
	    		}else {
	    			der.insertar(nuevo);
	    		}
	    	}
	    }
	
}
