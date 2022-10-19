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
		System.out.println("Ingresar apellido de futbolista: ");
		String apellido = input.nextLine();
		if (BuscarJugador(apellido,con)!=null) System.out.println("El futbolista ingresado ya existe.");
		else {
			System.out.println("Ingresar nombre: ");
			String nombre = input.nextLine();
			System.out.println("Ingresar telefono: ");
			int telefono = input.nextInt();
			System.out.println("Ingresar numero de documento: ");
			int docIdentidad = input.nextInt();
			System.out.println("Ingresar email: ");
			String mail = input.nextLine();
				try{
					String query = "SELECT idpais,nombre FROM pais";
					Statement st = con.createStatement();
					ResultSet rs= st.executeQuery(query);
					System.out.println("Escriba el nombre de id del pais que representa el jugador: ");
					while (rs.next()){
						System.out.println(rs.getInt("idpais")+" : "+rs.getString("nombre"));
					}
					int paisID = sc.nextInt();
					rs= st.executeQuery("SELECT nombre FROM pais WHERE idpais = "+paisID);
					System.out.println("¿Quiere agregar a "+nombre+" "+apellido+" a "+rs.getString("pais")+"? SI / NO");
					String opcion = sc.nextLine();
					boolean valido = false;
					while (!valido) {
						if (opcion.equals("SI")) {
							valido = true;
							// INSERTAR DATOS
							st.executeUpdate("INSERT INTO futbolista (nombre,apellido,docIdentidad,telefono,mail,idpais) VALUES('"+nombre+"','"+apellido+"','"+docIdentidad+"','"+telefono+"','"+mail+"','"+paisID+"')");
							System.out.println("Agregado exitosamente");
						} else {
							if (opcion.equals("NO")) {
								valido = true;
							} 
							else {
								System.out.println("Invalido");
								System.out.println("¿Quiere agregar a "+nombre+" "+apellido+" en "+rs.getString("pais")+"? SI / NO");
								opcion = sc.nextLine();
							}
						}
					}
					rs.close();
					st.close();
					} catch (java.sql.SQLException e) {
						System.out.println("Error de SQL: "+e.getMessage());
					}
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
			System.out.println("Ingresar sede: ");
			String nombre = input.nextLine();
			if (BuscarSede(nombre,con)!=null){
				try{
					Statement st = con.createStatement();
					System.out.println("Ingresar capacidad: ");
					int capacidad = input.nextInt();
					System.out.println("Ingresar pais: ");
					String pais = input.nextLine();
					ResultSet rs= st.executeQuery("SELECT * FROM pais WHERE nombre = "+pais);
					int res=st.executeUpdate("INSERT INTO sede (nombre,capacidad,idpais) VALUES('"+nombre+"','"+capacidad+"','"+rs.getInt("idpais")+"')");
					if (res==0) System.out.println("Agregado exitosamente");
					else System.out.println("Ocurrio un error");
					st.close();
				 } catch (java.sql.SQLException e) {
					System.out.println("Error de SQL: "+e.getMessage());
				 }
			else System.out.println("La sede ingresado ya existe.");	
		}
		
// busca una sede y la edita
		public static void EditarSede(Connection con) {
			System.out.println("Ingresar sede: ");
			String nombre = input.nextLine();
				try{
					if (BuscarSede(nombre,con)==null) System.out.println("La sede ingresada no existe.");
					else {
						Statement st = con.createStatement();	
						System.out.println("Ingresar capacidad: ");
						int capacidad = input.nextInt();
						System.out.println("Ingresar pais: ");
						String pais = input.nextLine();
						ResultSet rs= st.executeQuery("SELECT * FROM pais WHERE nombre = "+pais);
						int res=st.executeUpdate("UPDATE sede (nombre,capacidad,idpais) VALUES('"+nombre+"','"+capacidad+"','"+rs.getInt("idpais")+"') where nombre ="+nombre);
						if (res==0) System.out.println("Editado exitosamente");
						else System.out.println("Ocurrio un error");
						rs.close();
						st.close();
					}
				 } catch (java.sql.SQLException e) {
					 System.out.println("Error de SQL: "+e.getMessage());
				 }
		}
// busca una sede y la borra
		
		public static void EliminarSede(Connection con) {
			System.out.println("Ingresar sede: ");
			String nombre = input.nextLine();
				try{
					if (BuscarSede(nombre,con)!=null) {
						Statement st = con.createStatement();	
						st.executeUpdate("DELETE FROM sede WHERE nombre = "+nombre);
						System.out.println("Eliminado exitosamente");
						st.close();
					}
					else System.out.println("La sede ingresada no existe.");
				 } catch (java.sql.SQLException e) {
					 System.out.println("Error de SQL: "+e.getMessage());
				 }
		}
		
		
		
}
