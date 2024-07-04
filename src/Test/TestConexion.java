package Test;

import java.sql.SQLException;

import DAO.Conexion;
import HerramientasConexion.ConexionGlobal;

public class TestConexion {

	
	public static void main(String[] args) throws SQLException {
		
		ConexionGlobal.establecerConexio();
		ConexionGlobal.cerrarConexion();	
	}
	
}
