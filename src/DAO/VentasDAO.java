package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


import DAO.ModelsDAO.REGISTROVENTADET;
import HerramientasConexion.ConexionGlobal;
import Models.Proveedor;
import Models.Respuesta;
import Models.Venta;

public class VentasDAO {

	CallableStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Venta venta =null;	
	List<Proveedor> proveedores = null;
	String query = "";
	
	public Respuesta REGISTROVENTA(DAO.ModelsDAO.REGISTROVENTA registroventa) {
		respuesta = new Respuesta("",true,null);			
		query = "{call REGISTROVENTA(?,?,?,?,?,?,?,?,?,?,?)}";
		
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (CallableStatement) ConexionGlobal.connection.prepareCall(query);
			
			stm.setInt(1, registroventa.getParam_Id_Usuario());
			stm.setString(2, registroventa.getParam_Id_Cliente());
			stm.setInt(3, registroventa.getParam_NumProductos());
			stm.setFloat(4, registroventa.getParam_Cantidad());
			stm.setFloat(5, registroventa.getParam_PagoTarjeta());
			
			
			stm.setFloat(6, registroventa.getParam_PagoEfectivo());
			
			stm.setInt(7, registroventa.getParam_Descuento());
			stm.setFloat(8, registroventa.getParam_PagoCliente());
			stm.setFloat(9, registroventa.getParam_SubTotal());
			
			stm.registerOutParameter(10, java.sql.Types.INTEGER);
			stm.registerOutParameter(11, java.sql.Types.VARCHAR);
			
			stm.execute();
			
			System.out.println(stm.getInt(10));
			System.out.println(stm.getString(11));
			
			int objeto = stm.getInt(10);
			String mensaje = stm.getString(11);
			
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
	
	public Respuesta REGISTROVENTADET( REGISTROVENTADET registroventadet) {
		
		respuesta = new Respuesta("",true,null);			
		query = "{call REGISTROVENTADET(?,?,?,?,?,?,?)}";
		
		try { 
			
			ConexionGlobal.establecerConexio();
			stm = (CallableStatement) ConexionGlobal.connection.prepareCall("{call REGISTROVENTADET(?,?,?,?,?,?,?,?,?)}");
			
			stm.setInt(1, registroventadet.getParam_Id_Producto());
			stm.setInt(2, registroventadet.getParam_NumProductos());
			stm.setInt(3, registroventadet.getParam_Id_Venta());
			stm.setFloat(4, registroventadet.getParam_Precio());
			stm.setString(5, registroventadet.getParam_DescuentoM());
			stm.setInt(6, registroventadet.getParam_DescuentoEsp());
			stm.setString(7, registroventadet.getParam_Id_Cliente());
			
			stm.registerOutParameter(8, java.sql.Types.INTEGER);
			stm.registerOutParameter(9, java.sql.Types.VARCHAR);
			
			stm.execute();
			
			System.out.println(stm.getInt(8));
			System.out.println(stm.getString(9));
			
			int objeto = stm.getInt(8);
			String mensaje = stm.getString(9);
			
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
	
	public static void main(String[]args) {
		
		
		VentasDAO dao = new VentasDAO();
		Respuesta r = new Respuesta("Ok",true,null);
		REGISTROVENTADET registroventadet = new REGISTROVENTADET(1,1,1,2,"No",0,"1",0,"");
		
		//r = (Respuesta) dao.REGISTROVENTADET(registroventadet);
		
		System.out.println(r.getRespuesta()+"  "+r.getMensaje());
		
		
		
		DAO.ModelsDAO.REGISTROVENTA registroventa = new DAO.ModelsDAO.REGISTROVENTA(1, "1", 2, 50, 50, 100, 0, 100, 100, 0, null);
		
		r = (Respuesta) dao.REGISTROVENTA(registroventa);
		
		System.out.println(r.getMensaje()+" "+r.getRespuesta());
		
	}
	
	
	
	
}
