package PrototiposClases;

public class ClasePais {
	
	public String nombre;
	public String idioma;
	
	public ClasePais(String nombre, String idioma) {
		this.nombre = nombre;
		this.idioma = idioma;
	}
		
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
}
