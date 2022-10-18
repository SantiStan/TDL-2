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
	        			IngresarPais();        		
	        			break;
	        		case 2:
	        			IngresarJugador();
	        			break;
	        		case 3:
	        			IngresarSede();
	        			break;
	        		case 4:
	        			EditarSede();
	        			break;
	        		case 5:
	        			EliminarSede();
	        			break;
				}	
				
				System.out.println("Opción 1: Agregar pais.\nOpción 2: Agregar jugador.\nOpción 3: Agregar sede.\nOpción 4: Edicion sede.\nOpción 5: Eliminacion sede.");			
		}

		
		}catch(SQLException e) {        
			System.out.println(e.getMessage());
		}		
	}
	
	public static ClasePais IngresarPais() {
		
	}

}
