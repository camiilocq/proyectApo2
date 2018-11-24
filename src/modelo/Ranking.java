package modelo;

public class Ranking {

	private Avion firstAvion;
	
	private Avion raiz;
	
	public Ranking() {
		firstAvion = null;
		raiz = null;
	}

	public Avion getFirstAvion() {
		return firstAvion;
	}

	public void setFirstAvion(Avion firstAvion) {
		this.firstAvion = firstAvion;
	}

	public Avion getRaiz() {
		return raiz;
	}

	public void setRaiz(Avion raiz) {
		this.raiz = raiz;
	}
	
	public void agregarAvionLista(Avion nuevo) {
		if(firstAvion == null) {
			firstAvion = nuevo;
		} else {
			if(firstAvion.compareTo(nuevo.getNombre()) >= 0) {
				nuevo.setNext(firstAvion);
				firstAvion = nuevo;
			} else {
				agregarAvionLista(nuevo, firstAvion, firstAvion.getNext());
			}
		}
	}
	
	private void agregarAvionLista(Avion nuevo, Avion anterior, Avion siguiente) {
		if(siguiente == null) {
			anterior.setNext(nuevo);
		} else {
			if(anterior.compareTo(nuevo.getNombre()) < 0 && siguiente.compareTo(nuevo.getNombre()) >= 0) {
				nuevo.setNext(siguiente);
				anterior.setNext(nuevo);
			} else {
				agregarAvionLista(nuevo, siguiente, siguiente.getNext());
			}
		}
	}
	
	public void agregarAvionArbol(Avion nuevo) {
		if(raiz == null) {
			raiz = nuevo;
		} else {
			agregarAvionArbol(nuevo, raiz);
		}
	}

	private void agregarAvionArbol(Avion nuevo, Avion actual) {
		if(actual.compareTo(nuevo.getNombre()) <= 0) {
			if(actual.getDer() == null) {
				actual.setDer(nuevo);
			} else {
				agregarAvionArbol(nuevo, actual.getDer());
			}
		} else {
			if(actual.getIzq() == null) {
				actual.setIzq(nuevo);
			} else {
				agregarAvionArbol(nuevo, actual.getIzq());
			}
		}
	}
	
	public Avion buscarAvionArbol(String nombre) {
		return buscarAvionArbol(nombre, raiz);
	}
	
	private Avion buscarAvionArbol(String nombre, Avion actual) {
		if(actual != null) {
			if(actual.compareTo(nombre) == 0) {
				return actual;
			} else if(actual.compareTo(nombre) < 0) {
				return buscarAvionArbol(nombre, actual.getDer());
			} else {
				return buscarAvionArbol(nombre, actual.getIzq());
			}
		} else {
			return null;
		}
	}
	
	public Avion buscarAvionLista(String nombre) {
		Avion actual = null;
		if(firstAvion != null) {
			if(firstAvion.compareTo(nombre) == 0) {
				return firstAvion;
			} else {
				actual = firstAvion.getNext();
				boolean encontrado = false;
				while(actual != null && !encontrado) {
					if(actual.compareTo(nombre) == 0) {
						return actual;
					} else {
						actual = actual.getNext();
					}
				}
			}
		}
		return actual;
	}
	
}
