package PrototiposClases;

import java.sql.*;
import javax.sql.*;
import java.util.Scanner;

public class main {
	
	static Scanner input=new Scanner(System.in);	
	
	
	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/mundial_futbol_2022";
		
		try {
			Connection con = DriverManager.getConnection(url,"root","Santutu");
				
			int selection = 0;

		
			System.out.println("Opción 1: Agregar pais.\nOpción 2: Agregar jugador.\nOpción 3: Agregar sede.\nOpción 4: Edicion sede.\nOpción 5: Eliminacion sede.");
		
			while (selection<6){
					
			
				selection = input.nextInt();
			
				switch(selection){
	        		case 1:  		
	        			AgregarPais(con);
	        			break;

	        		case 2:               
	        			AgregarJugador(con);
	        			break;

	        		case 3:
	        			AgregarSede(con);
	        			break;
	            	
	        		case 4:
	        			EditarSede(con);
	        			break;
	            	
	        		case 5:
	        			EliminarSede(con);
	        			break;
	             
				}	
				
				System.out.println("Opción 1: Agregar pais.\nOpción 2: Agregar jugador.\nOpción 3: Agregar sede.\nOpción 4: Edicion sede.\nOpción 5: Eliminacion sede.");			
	     }

		
	}catch(SQLException e) {        
		System.out.println(e.getMessage());
	}
}

	
// METODOS DEL MENU
	

//Busca detro de la base de datos el pais que se pide.
	public static ClasePais BuscarPais(String nombre,Connection con) {
		ClasePais p = null;
		try {
			Statement st = con.createStatement();     
			ResultSet rs= st.executeQuery("SELECT * FROM pais"); 
			Boolean valido = true;
			while(rs.next() && valido){
				if(rs.getString("nombre").equals(nombre)) 
					p = new ClasePais(rs.getString("nombre"), rs.getString("idioma"));
			}
			rs.close();      
			st.close();    
		}catch(SQLException e) {        
			System.out.println(e.getMessage());
		}
		return p;
	}
	
	
// Busca un pais en la base de datos, y en caso de no encontrarla, este lo agrega
	public static void AgregarPais(Connection con){				
		System.out.println("Escriba el nombre del pais que quiere agregar: ");
		String nombre = input.nextLine();
		if(nombre == null) {
			try {
				System.out.print("Escriba el idioma del pais: ");
				String idioma = input.nextLine();
				String query = "“INSERT INTO pais values (?,?)";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, nombre);
				st.setString(2, idioma);
				System.out.println("Agregado exitosamente");
			}catch(SQLException e) {        
				System.out.println(e.getMessage());
			}
		}
		else System.out.print("El pais que esta intentanto ingresar ya existe");		
	}
	
//Busca detro de la base de datos el pais que se pide.
	public static ClaseJugador BuscarJugador(String apellido,Connection con) {
		ClaseJugador j = null;
		try {
			Statement st = con.createStatement();     
			ResultSet rs= st.executeQuery("SELECT * FROM jugador"); 
			Boolean valido = true;
			while(rs.next() && valido){
				if(rs.getString("apellido").equals(apellido)) { 
					j = new ClaseJugador(rs.getString("nombre"), rs.getString("apellido"), rs.getString("docIdentidad"), rs.getString("telefono"), rs.getString("email"), rs.getInt("idpais"));
				}			
			}
			rs.close();      
			st.close();    
		}catch(SQLException e) {        
			System.out.println(e.getMessage());
		}
		return j;
	}
		
// busca un pais en la base de datos, y en caso de no encontrarla, este lo agrega
		
	public static void AgregarJugador(Connection con){	
		System.out.println("Escriba el nombre del pais que quiere agregar: ");
		String apellido = input.nextLine();
		if(apellido == null) {
			try {
				System.out.print("Escriba el idioma del pais: ");
				String nombre = input.nextLine();
				String docIdentidad = input.nextLine();
				String telefono = input.nextLine();
				String email = input.nextLine();
				
				String query = "“INSERT INTO jugadores values (?,?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, nombre);
				st.setString(2, apellido);
				st.setString(3, docIdentidad);
				st.setString(4, telefono);
				st.setString(5, email);
				System.out.println("Agregado exitosamente");
			}catch(SQLException e) {        
				System.out.println(e.getMessage());
			}
		}
		else System.out.print("El pais que esta intentanto ingresar ya existe");
	}

// Busca detro de la base de datos el pais que se pide.
	public static ClaseSede BuscarSede(String nombre,Connection con) {
		ClaseSede s = null;
		try {
			Statement st = con.createStatement();     
			ResultSet rs= st.executeQuery("SELECT * FROM pais"); 
			Boolean valido = true;
			while(rs.next() && valido){
				if(rs.getString("nombre").equals(nombre)) 
					s = new ClaseSede(rs.getString("nombre"), rs.getInt("capacidad"), rs.getInt("idpais"));
			}
			rs.close();      
			st.close();    
		}catch(SQLException e) {        
			System.out.println(e.getMessage());
		}
		return s;
	}
		
		
//busca una sede en la base de datos, y en caso de no encontrarla, este lo agrega
		public static void AgregarSede(Connection con){				
			try {
				
			}catch(SQLException e) {        
				System.out.println(e.getMessage());
			}
		}
		
// busca una sede y la edita
		public static void EditarSede(Connection con) {
			try {
				
			}catch(SQLException e) {        
				System.out.println(e.getMessage());
			}
		}
// busca una sede y la borra
		
		public static void EliminarSede(Connection con) {
			try {
				
			}catch(SQLException e) {        
				System.out.println(e.getMessage());
			}
		}
		
		
		
}
