package Models;

import java.util.List;

public class Carrito {

	private String nombre;
	public List<Producto> carritos;
	
	public Carrito(String nombre, List<Producto> carritos) {
		this.nombre = nombre;
		this.carritos = carritos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Producto> carritos) {
		this.carritos = carritos;
	}
	
	
	
}
