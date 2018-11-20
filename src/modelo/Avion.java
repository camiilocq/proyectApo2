package modelo;

import interfaces.Disparable;

public class Avion extends Ente {
	
	private int vida;
	private int puntuacion;
	
	private double velocidad;
	
	private Disparo disparo;
	
	private String nombre;
	
	
	public Avion(double velocidad, int vida,String nombre,int puntuacion,Disparo disparo,double posX, double posY) {
		super(posX,posY);
		this.disparo = disparo;
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.vida = vida;
		this.velocidad = velocidad;
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
	
	

	
}
