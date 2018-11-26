package modelo;

import java.util.ArrayList;

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
	
	public void agregarAvionLista(Avion nuevo, Avion anterior, Avion siguiente) {
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

	public void agregarAvionArbol(Avion nuevo, Avion actual) {
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
	
	public Avion buscarAvionArbol(String nombre, Avion actual) {
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
	
	public Avion ultimoJugador() {
		Avion toReturn = null;
		if(firstAvion != null) {
			if(firstAvion.getNext() == null) {
				return firstAvion;
			} else {
				toReturn = firstAvion.getNext();
				while(toReturn.getNext() != null) {
					toReturn = toReturn.getNext();
				}
			}
		}
		return toReturn;
	}
	
	public Avion primerJugador() {
		return firstAvion;
	}
	
	public ArrayList<Avion> listaJugadores() {
		if(raiz == null) {
			return null;
		} else {
			ArrayList<Avion> lista = new ArrayList<Avion>();
			raiz.inOrden(lista);
			return lista;
		}
	}
	
	public ArrayList<Avion> ordenarPorNombre() {
		if(raiz == null) {
			return null;
		} else {
			ArrayList<Avion> lista = listaJugadores();
			for(int i = 1; i < lista.size(); i++) {
				for(int j = i; j > 0 && lista.get(j-1).compareTo(lista.get(j).getNombre()) > 0; j--) {
					Avion a = lista.get(j-1);
					lista.remove(j-1);
					lista.add(j-1, lista.get(j));
					lista.remove(j);
					lista.add(j, a);
				}
			}
			return lista;
		}
		
	}
	
	public ArrayList<Avion> ordenarPorPuntos() {
		if(raiz == null) {
			return null;
		} else {
			ArrayList<Avion> lista = listaJugadores();
			for(int i = 0; i < lista.size()-1; i++) {
				Avion menor = lista.get(i);
				int cual = i;
				for(int j = i+1; j < lista.size(); j++) {
					if(lista.get(j).compareTo(menor.getNombre()) < 0) {
						menor = lista.get(j);
						cual = j;
					}
				}
				Avion a = lista.get(i);
				lista.remove(i);
				lista.add(i, menor);
				lista.remove(cual);
				lista.add(cual, a);
			}
			return lista;
		}
		
	}
	
	public int busquedaBinariaPorNombre(String nombre) {
		if(raiz == null) {
			return -1;
		} else {
			ArrayList<Avion> lista = ordenarPorNombre();
			int inicio = 0, fin = lista.size()-1, pos = -1, medio;
			boolean finish = false;
			while(!finish && inicio <= fin) {
				medio = (fin - inicio)/2;
				if(lista.get(medio).compareTo(nombre) == 0) {
					pos = medio;
					finish = true;
				} else if(lista.get(medio).compareTo(nombre) > 0) {
					fin = medio-1;
				} else {
					inicio = medio+1;
				}
			}
			return pos;
		}
	}
	
	public Avion buscarPorNombre(String nombre) {
		if(raiz == null) {
			return null;
		} else {
			ArrayList<Avion> lista = ordenarPorNombre();
			int pos = busquedaBinariaPorNombre(nombre);
			if(pos == -1) {
				return null;
			} else {
				return lista.get(pos);
			}
		}
	}
	
	public ArrayList<Avion> TotalJugadores() {
		if(raiz == null) {
			return null;
		} else {
			ArrayList<Avion> lista = new ArrayList<Avion>();
			raiz.inOrden(lista);
			return lista;
		}
	}
	
}
