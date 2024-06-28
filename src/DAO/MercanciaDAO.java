package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.ModelsDAO.REGISTROMERCANCIA;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;
import Models.Venta;

public class MercanciaDAO {
	
	String query="";
	CallableStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Venta venta =null;	
	
	public Respuesta registroMercacia(REGISTROMERCANCIA registromercancia) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		query = "{call REGISTROMERCANCIA(?,?,?,?,?,?,?,?)}";
		
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (CallableStatement) ConexionGlobal.connection.prepareCall(query);
			
			stm.setInt(1, registromercancia.getParam_Id_Producto());
			stm.setInt(2, registromercancia.getParam_Id_Proveedor());
			stm.setInt(3, registromercancia.getParam_Cantidad());
			stm.setFloat(4, registromercancia.getParam_Total());
			stm.setString(5, registromercancia.getParam_Tipo());
			stm.setFloat(6, registromercancia.getParam_Precio_Unitario());			
			stm.registerOutParameter(7, java.sql.Types.INTEGER);
			stm.registerOutParameter(8, java.sql.Types.VARCHAR);
			
			stm.execute();
			
			System.out.println(stm.getInt(7));
			System.out.println(stm.getString(8));
			
			int objeto = stm.getInt(7);
			String mensaje = stm.getString(8);
			
			respuesta.setRespuesta(objeto);
			respuesta.setMensaje( mensaje);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar correr procedimiento"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return respuesta;
	}

}
