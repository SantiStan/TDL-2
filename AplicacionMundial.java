package PrototiposClases;


import java.sql.*;
import java.util.Scanner;

public class main {
	
	static Scanner input=new Scanner(System.in);	
	
	
	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/mundial_futbol_2022";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url,"root","Santutu");
			

			System.out.println("Elija una opcion:\n-Opción 0: Salir del sistema.\n-Opción 1: Agregar pais.\n-Opción 2: Agregar jugador.\n-Opción 3: Agregar sede.\n-Opción 4: Edicion sede.\n-Opción 5: Eliminacion sede.");
		
			int selection = input.nextInt();
			
			while (selection!=0){
			
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
	        			
	        		default: 
	        			System.out.println("Error: Opcion invalida.");
	             
				}	
				
				System.out.println("Opción 1: Agregar pais.\nOpción 2: Agregar jugador.\nOpción 3: Agregar sede.\nOpción 4: Edicion sede.\nOpción 5: Eliminacion sede.");			
				selection = input.nextInt();
		}
		
		System.out.print("Usted salio del menu, hasta luego.");
		
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
		System.out.println("Ingresar el nombre del pais: ");
		input.nextLine();
		String nombre = input.nextLine();
		if (BuscarPais(nombre,con)!=null) {
			try{
				System.out.println("Ingresar idioma: ");
				String idioma = input.nextLine();
				String query = "INSERT INTO pais (nombre,idioma) VALUES( ? , ? )";
				PreparedStatement pst = con.prepareStatement(query);
				pst.clearParameters();
				pst.setString(1,nombre);
				pst.setString(2,idioma);
				pst.executeUpdate();
				System.out.println("Se agrego exitosamente el pais");
				pst.close();
			} catch (java.sql.SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		else System.out.println("El pais ingresado ya existe.");		
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
		input.nextLine();
		System.out.println("Ingresar apellido del jugador: ");
		String nombre = input.nextLine();
		System.out.println("Ingresar el nombre: ");
		String apellido = input.nextLine();
		if (BuscarFutbolista(apellido,con)!=null) System.out.println("El futbolista ingresado ya existe.");
		else {
			System.out.println("Ingresar telefono: ");
			int telefono = input.nextInt();
			input.nextLine();
			System.out.println("Ingresar documento de identidad: ");
			int docIdentidad = input.nextInt();
			input.nextLine();
			System.out.println("Ingresar mail: ");
			String mail = input.nextLine();
				try{
					String query = "SELECT idpais,nombre FROM pais";
					Statement st = con.createStatement();
					ResultSet rs= st.executeQuery(query);
					System.out.println("Elija pais:");
					while (rs.next()){
						System.out.println(rs.getInt("idpais")+" : "+rs.getString("nombre"));
					}
					int paisID = input.nextInt();
					input.nextLine(); 
					query = "SELECT * FROM pais WHERE idpais = ?";
					PreparedStatement pst = con.prepareStatement(query);
					pst.clearParameters();
					pst.setInt(1,paisID);
					ResultSet rs2 = pst.executeQuery();
					rs2.next();
					System.out.println("¿Desea agregar a "+nombre+" "+apellido+" a "+rs2.getString("nombre")+"? SI / NO");
					String opcion = input.nextLine();
					
					while (!opcion.equals("SI") && !opcion.equals("NO")) {
						System.out.println("Opcion invalida, intente de nuevo");
						opcion = input.nextLine();
					}
						 if (opcion.equals("SI")) {
							 pst = con.prepareStatement("INSERT INTO futbolista (nombre,apellido,docIdentidad,telefono,email,idpais) VALUES(?,?,?,?,?,?)");
							 pst.setString(1,nombre);
							 pst.setString(2,apellido);
							 pst.setInt(3,docIdentidad);
							 pst.setInt(4,telefono);
							 pst.setString(5, mail);
							 pst.setInt(6,paisID );
							 pst.executeUpdate();
							 pst.close();
							 System.out.println("Agregado exitosamente");
						 }
					rs2.close();
					rs.close();
					st.close();
					pst.close();
				 } catch (java.sql.SQLException e) {
					System.out.println(e.getMessage());
				 }
		}
	}

private static Object BuscarFutbolista(String apellido, Connection con) {
	// TODO Auto-generated method stub
	return null;
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
			input.nextLine();
			System.out.println("Ingresar sede: ");
			String nombre = input.nextLine();
			if (BuscarSede(nombre,con)==null){
				try{
					System.out.println("Ingresar capacidad: ");
					int capacidad = input.nextInt();
					input.nextLine();
					System.out.println("Ingresar pais: ");
					String pais = input.nextLine();
					String query = "SELECT * FROM pais WHERE nombre = ?";
					PreparedStatement st = con.prepareStatement(query);
					st.clearParameters();
					st.setString(1,pais);
					ResultSet rs = st.executeQuery();
					rs.next();
					query = "INSERT INTO sede (nombre,capacidad,idpais) VALUES(?,?,?)";
					st = con.prepareStatement(query);
					st.clearParameters();
					st.setString(1,nombre);
					st.setInt(2,capacidad);
					st.setInt(3,rs.getInt("idpais"));
					st.executeUpdate();
					st.close();
					System.out.print("La sede fue agregada con exito");
				 } catch (java.sql.SQLException e) {
					System.out.println(e.getMessage());
				 }
			}
			else  System.out.println("La sede ingresado ya existe.");	
		}
		
// busca una sede y la edita
		public static void EditarSede(Connection con) {
			input.nextLine();
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
					 System.out.println(e.getMessage());
				 }
		}
// busca una sede y la borra
		
		public static void EliminarSede(Connection con) {
			input.nextLine();
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
					 System.out.println(e.getMessage());
				 }
		}
		
}
