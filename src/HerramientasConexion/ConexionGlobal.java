package HerramientasConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionGlobal {

	private static ConexionGlobal instancia = null;
	
	private static String url = "jdbc:mysql://localhost:3306/Ferreteria";
	private static String user = "root";
	private static String pass = "rootroque";
	public static Connection connection;
	
	private ConexionGlobal() {
		super();
	}
	
	
	public static ConexionGlobal getInstance() {
		
		if(instancia == null)
			instancia  = new ConexionGlobal();
		
		
		
		return instancia;
	}
	
	public static Connection establecerConexio() {
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = (Connection) DriverManager.getConnection(url,user,pass);
	            //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/farmacia","root","root");
	            System.out.println("Conexion establecida correctamente desde Conexion Global");
	            return connection;
	        } catch (Exception e) {
	            System.out.println("Problemas al conectar "+e.getMessage());
	            establecerConexionRemota();
	        }
	        return null;
	}
	
	public static void reasignacionVariables() {
		url = "jdbc:mysql://viaduct.proxy.rlwy.net:52440/railway";
		user = "root";
		pass = "YaGWTBPishvfDOuZaJjnTxbsZHeAMXuG";
	}
	
	public static Connection establecerConexionRemota() {
		try {
			reasignacionVariables();
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url,user,pass);
            //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/farmacia","root","root");
            System.out.println("Conexion establecida correctamente desde Conexion Global");
            return connection;
        } catch (Exception e) {
            System.out.println("Problemas al conectar "+e.getMessage());
        }
        return null;
	}
	
	public static Connection cerrarConexion() throws SQLException{
       connection.close();
       System.out.println("Se ha cerrado la conexion...");
       return null;
   }

	
	
}
