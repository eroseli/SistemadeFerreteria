package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {

	private static String url = "jdbc:mysql://localhost:3306/Ferreteria?noAccessToProcedureBodies = false ";
	private static String user = "root";
	private static String pass = "rootroque";
	
	
	public static Connection connection;
	
	public Conexion() {
		
	}
	
	public Connection establecerConexio() {
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = (Connection) DriverManager.getConnection(url,user,pass);
	            //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/farmacia","root","root");
	            System.out.println("Conexion establecida correctamente ");
	            return connection;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}
	
	public Connection cerrarConexion() throws SQLException{
        connection.close();
        System.out.println("Se ha cerrado la conexion...");
        return null;
    }

	

	
}
