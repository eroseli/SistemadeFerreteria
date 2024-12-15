package DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.MercanciaDetDAO;
import DAO.ModelsDAO.REGISTROMERCANCIA;
import HerramientasConexion.ConexionGlobal;
import Models.MercanciaDetView;
import Models.Respuesta;
import Models.Venta;

public class MercanciaDAO {
	
	String query="";
	CallableStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	DAO.ModelsDAO.MercanciaDAO  mercancia =null;	
	MercanciaDetView mercanciaDet = null;
	
	ArrayList<DAO.ModelsDAO.MercanciaDAO> mercancias = new ArrayList<>();
	ArrayList<MercanciaDetView> mercanciasDet = new ArrayList<>();
	
	public Respuesta REGISTROMERCANCIAPRO(DAO.ModelsDAO.REGISTROMERCANCIAPRO registromercanciapro) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		query = "{call REGISTROMERCANCIAPRO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		try {
			
			ConexionGlobal.establecerConexio();
			stm = ConexionGlobal.connection.prepareCall(query);
			
			stm.setInt(1, registromercanciapro.getTipoOperacion());
			stm.setInt(2,registromercanciapro.getIdHistorialCompra() );
			stm.setString(3, registromercanciapro.getIdProveedor());
			stm.setInt(4, registromercanciapro.getTotalProductos());
			stm.setFloat(5, registromercanciapro.getTotalCompra());
			stm.setString(6,registromercanciapro.getDescripcion());
			stm.setString(7,registromercanciapro.getEstadoPago());
			stm.setString(8,registromercanciapro.getTipo());
			
			stm.setInt(9,registromercanciapro.getIdProducto() ); // detalle de la compra
			stm.setInt(10, registromercanciapro.getCantidad());
			stm.setFloat(11, registromercanciapro.getPrecioUnitario());
			stm.setFloat(12, registromercanciapro.getPrecioTotal());
			stm.registerOutParameter(13, java.sql.Types.INTEGER);
			stm.registerOutParameter(14, java.sql.Types.VARCHAR);
			
			stm.execute();
			
			int objeto = stm.getInt(13);
			String mensaje = stm.getString(14);
			
			respuesta.setMensaje(mensaje);
			respuesta.setRespuesta(objeto);
			
			System.out.println("Id Historial Compra "+objeto);
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al intentar correr procedimiento"+e.getMessage(),false,null);
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
	
	public Respuesta obtenerCompras(Date fechaInicio ,Date fechaFinal ) {
		
		respuesta = new Respuesta("",true,null);
		query  = "select * from HistorialCompras where Estatus = 'ACTIVO' and "
				+ " DATE(FechaRegistro) BETWEEN '"+fechaInicio+"' AND '"+fechaFinal+"' ORDER BY FechaRegistro desc;";
		mercancias = new ArrayList<DAO.ModelsDAO.MercanciaDAO>();
		
		try {
			System.out.println(query);
			mercancias.clear();
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				mercancia = new DAO.ModelsDAO.MercanciaDAO(
						resultados.getInt("Id_HistorialCompra"),
						resultados.getString("Id_Proveedor"),
						resultados.getInt("TotalProductos"),
						resultados.getInt("TotalCompra"),
						resultados.getString("Descripcion"),
						resultados.getString("EstadoPago"),
						resultados.getString("TipoOperacion"),
						resultados.getDate("FechaRegistro"),
						resultados.getString("Estatus")				
				);
				
				mercancias.add(mercancia);
				
			}
			
			respuesta.setRespuesta(mercancias);
			
		}  catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error : "+e.getMessage());
				respuesta = new Respuesta("Error : "+e.getMessage(),false,null);
			}
		}
		
		return respuesta;
		
	}
	
	public Respuesta obtenerDetalleCompra(int Id_HistorialCompra) {
		
		respuesta = new Respuesta("",true,null);
		query  = "\n"
				+ "SELECT HC.*, P.Nombre, P.Descripcion, P.Codigo FROM HistorialComprasDet HC INNER JOIN Productos P ON HC.Id_producto = P.Id_Producto "
				+ "WHERE HC.Id_HistorialCompraDet = "+Id_HistorialCompra+" AND HC.Estatus = 'ACTIVO' ORDER BY HC.Id_HistorialCompraDet desc;";
		mercanciasDet = new ArrayList<MercanciaDetView>();
		
		try {
			System.out.println(query);
			mercanciasDet.clear();
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				mercanciaDet = new MercanciaDetView(
						resultados.getInt("Id_HistorialCompraDet"),
						resultados.getInt("Id_HistorialCompra"),
						resultados.getInt("Id_Producto"),
						resultados.getString("Nombre"),
						resultados.getString("Descripcion"),
						resultados.getString("Codigo"),
						resultados.getInt("Cantidad"),
						resultados.getFloat("PrecioUnitario"),
						resultados.getFloat("PrecioTotal"),
						resultados.getString("Estatus")
				);
				
				mercanciasDet.add(mercanciaDet);
				
			}
			
			respuesta.setRespuesta(mercanciasDet);
			
		}  catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error : "+e.getMessage());
				respuesta = new Respuesta("Error : "+e.getMessage(),false,null);
			}
		}
		
		return respuesta;
		
	}
	

}
