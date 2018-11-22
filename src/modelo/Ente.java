package modelo;


import java.io.Serializable;

import interfaces.Movible;

public abstract class Ente implements Movible, Serializable {
	private double posX;
	private double posY;

	public Ente(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	
}
