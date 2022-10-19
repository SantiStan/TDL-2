package PrototiposClases;

import java.sql.*;
import javax.sql.*;

public class ClaseJugador {
	
	public String nombre;
	public String apellido;
	public String docId;
	public String teléfono;
	public String email;
	public int pais;
	
	public ClaseJugador(String nombre, String apellido, String docId, String teléfono, String email, int pais) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.docId = docId;
		this.teléfono = teléfono;
		this.email = email;
		this.pais = pais;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getTeléfono() {
		return teléfono;
	}
	public void setTeléfono(String teléfono) {
		this.teléfono = teléfono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPais() {
		return pais;
	}
	public void setPais(int pais) {
		this.pais = pais;
	}
		

}
