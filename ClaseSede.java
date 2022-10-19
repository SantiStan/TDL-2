package PrototiposClases;

public class ClaseSede {
		
	public String nombre;
	public int capacidad;
	public int pais;
		
	public ClaseSede(String nombre, int capacidad, int pais) {
		super();
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.pais = pais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getPais() {
		return pais;
	}
	public void setPais(int pais) {
		this.pais = pais;
	}
			
}
