package DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.ProductosVentaDAO;
import DAO.ModelsDAO.Proveedor;
import DAO.ModelsDAO.REGISTROVENTADET;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;
import DAO.ModelsDAO.Venta;
import DAO.ModelsDAO.VentaTotalesDAO;

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
	
	public Respuesta obtenerProductosVenta(int idHistorial) {
		
		respuesta = new Respuesta("",true,null);
		List<ProductosVentaDAO> productosventa = new ArrayList<ProductosVentaDAO> ();
		ProductosVentaDAO productoVentaDAO = new ProductosVentaDAO();
		query = "select H.Id_Historial, P.Codigo, P.Nombre, P.Descripcion, H.Cantidad, "
				+ "case when DescuentoM = 'Si' then P.P_Mayoreo else P.P_publico end as Precio, H.Precio Monto, H.DescuentoM, "
				+ "case when H.DescuentoEsp = 0 then \"Si\" else \"No\" end as DescuentoEsp, H.FechaRegistro "
				+ "from historial H right join productos P on H.Id_Producto = P.Id_producto "
				+ "where Id_venta =  "+idHistorial+" ;";
		
		try {
			System.out.println(query);
			
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {
				
				productoVentaDAO = new ProductosVentaDAO(
						resultados.getInt("Id_Historial"),
						resultados.getString("Codigo"),
						resultados.getString("Nombre"),
						resultados.getString("Descripcion"),
						resultados.getInt("Cantidad"),
						resultados.getFloat("Precio"),
						resultados.getFloat("Monto"),
						resultados.getString("DescuentoM"),
						resultados.getString("DescuentoEsp"),
						resultados.getDate("FechaRegistro")
						);		
				productosventa.add(productoVentaDAO);
			}
			
			respuesta.setRespuesta(productosventa);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Productos del Cliente"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
		
	}
	
	public Respuesta obtenerVentasFecha() {
		
		respuesta = new Respuesta("",true,null);
		List<Venta> ventas = new ArrayList<Venta>();
		Venta venta = new Venta();
		
		query = "select \n"
				+ "	ROW_NUMBER() OVER (ORDER BY v.ID_Venta) AS consecutivo, v.ID_Venta,"
				+ "CASE WHEN u.nombre is null then \"Mostrador\" ELSE concat (u.nombre, \" \",u.apaterno) end as usuario,"
				+ " CASE when c.Nombre is null then \"\" ELSE concat(c.Nombre,\" \",c.Apaterno) end as cliente, v.NumProductos productos, v.PagoTarjeta tarjeta, v.PagoEfectivo efectivo,"
				+ "    v.Descueto Descuento, v.FechaRegistro fecha, v.Hora from Ventas v left join Usuarios u on v.ID_Usuario = u.Id_Usuario "
				+ "	left join Clientes c on  v.idCliente = c.Id_Cliente;";		
		try {
			
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {
				
				venta = new Venta(
						resultados.getInt("consecutivo"),
						resultados.getInt("ID_Venta"),
						resultados.getString("usuario"),
						resultados.getString("cliente"),
						resultados.getInt("productos"),
						resultados.getFloat("tarjeta"),
						resultados.getFloat("efectivo"),
						resultados.getInt("Descuento"),
						resultados.getDate("fecha"),
						resultados.getTime("Hora")
						);		
				ventas.add(venta);
			}
			
			respuesta.setRespuesta(ventas);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
		
	}
	
	public Respuesta obtenerVentas(int tipoConsulta, Date fechaInicial, Date fechaFinal) {
		
		respuesta = new Respuesta("",true,null);
		List<Venta> ventas = new ArrayList<Venta>();
		Venta venta = new Venta();
		
		query = "select \n"
				+ "	ROW_NUMBER() OVER (ORDER BY v.ID_Venta) AS consecutivo, v.ID_Venta,"
				+ "CASE WHEN u.nombre is null then \"Mostrador\" ELSE concat (u.nombre, \" \",u.apaterno) end as usuario,"
				+ " CASE when c.Nombre is null then \"\" ELSE concat(c.Nombre,\" \",c.Apaterno) end as cliente, v.NumProductos productos, v.PagoTarjeta tarjeta, v.PagoEfectivo efectivo,"
				+ "    v.Descueto Descuento, v.FechaRegistro fecha, v.Hora from Ventas v left join Usuarios u on v.ID_Usuario = u.Id_Usuario "
				+ "	left join Clientes c on  v.idCliente = c.Id_Cliente ";		
		
		switch(tipoConsulta) {
		case 1://Consulta con fechas
			query = query + "where v.FechaRegistro between '"+fechaInicial+"' and '"+fechaFinal+"' order by v.FechaRegistro asc;";
			break;
		case 2:// consulta inicial
			query = query + " order by v.FechaRegistro asc; ";
			break;
		}
		
		try {
			
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {
				
				venta = new Venta(
						resultados.getInt("consecutivo"),
						resultados.getInt("ID_Venta"),
						resultados.getString("usuario"),
						resultados.getString("cliente"),
						resultados.getInt("productos"),
						resultados.getFloat("tarjeta"),
						resultados.getFloat("efectivo"),
						resultados.getInt("Descuento"),
						resultados.getDate("fecha"),
						resultados.getTime("Hora")
						);		
				ventas.add(venta);
			}
			
			respuesta.setRespuesta(ventas);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
		
	}
	
	public Respuesta obtenerValoresTotalesVentas(Date fechaInicio, Date fechaFinal) {
		
		VentaTotalesDAO ventaTotalesDAO = new VentaTotalesDAO();
		Respuesta respuesta = new Respuesta("Ok",true,null);
		query= "select count(ID_Venta) totalVentas ,round(sum(V.Cantidad),2) ventaTotal, sum(NumProductos) totalProductos, "
				+ "	round(avg(Cantidad),2) PromedioTicket "
				+ "	from Ventas as V where V.FechaRegistro between '"+fechaInicio+"' and '"+fechaFinal+"';";
		try {
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {			
				ventaTotalesDAO = new VentaTotalesDAO(
						fechaInicio,
						fechaFinal,
						resultados.getInt("totalVentas"),
						resultados.getInt("totalProductos"),
						resultados.getFloat("ventaTotal"),
						0f,//descuentos aplicados
						"",//producto estrella
						resultados.getFloat("promedioTicket")
						);
			}			
			respuesta.setRespuesta(ventaTotalesDAO);
			
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
	
	}
	
	public Respuesta obtenerTotalesHistorialProductoEstrella(Date fechaInicio, Date fechaFinal) {
		
		VentaTotalesDAO ventaTotalesDAO = new VentaTotalesDAO();
		Respuesta respuesta = new Respuesta("Ok",true,null);
		query= "select H.Id_Producto, count(H.Id_Producto) cantidad, concat(P.Codigo, ' - ', P.Nombre) productoEstrella"
				+ "from Ventas V inner join historial H on V.ID_Venta = H.Id_Venta "
				+ "inner join Productos P on H.Id_Producto = P.Id_Producto "
				+ "where V.fechaRegistro between '"+fechaInicio+"' and '"+fechaFinal+"' group by Id_Producto limit 1;";
		
		try {
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {			
				ventaTotalesDAO = new VentaTotalesDAO(
						fechaInicio,
						fechaFinal,
						0,
						0,
						0f,
						0f,//descuentos aplicados
						 resultados.getInt("cantidad") +" "+ resultados.getString("productoEstrella"),//producto estrella
						0f
						);
			}			
			respuesta.setRespuesta(ventaTotalesDAO);
			
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
		
	}
	
	
public Respuesta obtenerTotalesHistorialDescuentosAplicados(Date fechaInicio, Date fechaFinal) {
		
		VentaTotalesDAO ventaTotalesDAO = new VentaTotalesDAO();
		Respuesta respuesta = new Respuesta("Ok",true,null);
		query= "select count(*) DescuentosAplicados from historial H where  H.DescuentoM = 'SI' or DescuentoEsp = 1 "
				+ "and H.FechaRegistro between '"+fechaInicio+"' and '"+fechaFinal+"';"
				+ "";
		
		try {
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {			
				ventaTotalesDAO = new VentaTotalesDAO(
						fechaInicio,
						fechaFinal,
						0,
						0,
						0f,
						0f,//descuentos aplicados
						 resultados.getInt("cantidad") +" "+ resultados.getString("productoEstrella"),//producto estrella
						0f
						);
			}			
			respuesta.setRespuesta(ventaTotalesDAO);
			
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
		
	}
	
	public  Respuesta obtenerHistorialProducto(int idHistorial) {
		
		VentasDAO dao =  new VentasDAO();
		ProductosVentaDAO productoVentaDAO = null;
		Respuesta respuesta = new Respuesta("Ok",true,null);
		query = "select H.Id_Historial, P.Codigo, P.Nombre, P.Descripcion, H.Cantidad, case when DescuentoM = 'Si' "
				+ " then P.P_Mayoreo else P.P_publico end as Precio, H.Precio Monto, H.DescuentoM, case when H.DescuentoEsp = 0 "
				+ " then 'Si' else 'No' end as DescuentoEsp, H.FechaRegistro "
				+ " from historial H right join productos P on H.Id_Producto = P.Id_producto "
				+ " where H.Id_Historial = "+idHistorial+";";
		
		try {
			System.out.println(query);
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {			
				productoVentaDAO = new ProductosVentaDAO(
						resultados.getInt("Id_Historial"),
						resultados.getString("Codigo"),
						resultados.getString("Nombre"),
						resultados.getString("Descripcion"),
						resultados.getInt("Cantidad"),
						resultados.getFloat("Precio"),
						resultados.getFloat("Monto"),
						resultados.getString("DescuentoM"),
						resultados.getString("DescuentoEsp"),
						resultados.getDate("FechaRegistro")
						);
				System.out.println(productoVentaDAO.getCodigo());
			}			
			respuesta.setRespuesta(productoVentaDAO);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
			e.printStackTrace();
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
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
