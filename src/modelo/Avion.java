package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import interfaces.Disparable;

public class Avion extends Ente {
	
	private int vida;
	private int puntuacion;
	private Avion der;
	private Avion izq;
	private int tiempo;
	
	private double velocidad;
	
	private Disparo disparo;
	
	private String nombre;
	
	
	public Avion(int tiempo, double velocidad, int vida,String nombre,int puntuacion,Disparo disparo,double posX, double posY) {
		super(posX,posY);
		this.tiempo = tiempo;
		this.disparo = disparo;
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.vida = vida;
		this.velocidad = velocidad;
		der = izq = null;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
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

	@Override
	public boolean isMovible() {
		// TODO Auto-generated method stub
		return false;
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
	
}
