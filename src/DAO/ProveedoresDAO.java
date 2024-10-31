package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.ModelsDAO.Proveedor;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class ProveedoresDAO {
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Proveedor proveedor =null;	
	List<Proveedor> proveedores = null;
	String query = "";
	
	public Respuesta obtenerCantidadProveedores() {

		respuesta = new Respuesta("",true,null);
		query = "select COUNT(Id_Proveedor) Cantidad from proveedores";
		int cantidad = 0;
		
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();			
			while(resultados.next()) {
				cantidad= resultados.getInt("Cantidad");	
			}
			
			respuesta.setRespuesta(cantidad);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener Cantidad de Clientes"+e.getMessage(), false, null);
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
	
	public Respuesta obtenerProveedores() {
		
		respuesta = new Respuesta("",true,null);
		proveedor = new Proveedor();
		query = "select * from proveedores;";
		proveedores = new ArrayList<Proveedor>();
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			while(resultados.next()) {
				
				proveedor = new Proveedor(
						resultados.getString("Id_Proveedor"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getString("Empresa"),
						resultados.getString("Direccion"),
						resultados.getDate("FechaRegistro"),
						resultados.getString("TipoProducto"),
						resultados.getString("NotasAdicionales")
						);
				proveedores.add(proveedor);
			}
			
			respuesta.setRespuesta(proveedores);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los proveedores"+e.getMessage(), false, null);
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
	
	public Respuesta obtenerProveedorDescripcion(String descripcionNombre) {
		
		respuesta = new Respuesta("",true,null);
		query  = "select * from proveedores where nombre like '%"+descripcionNombre+"%' and Estatus = 'ACTIVO' ";
		proveedores = new ArrayList<Proveedor>();
		
		try {
			
			proveedores.clear();
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				proveedor = new Proveedor(
						resultados.getString("Id_Proveedor"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getString("Empresa"),
						resultados.getString("Direccion"),
						resultados.getDate("FechaRegistro"),
						resultados.getString("TipoProducto"),
						resultados.getString("NotasAdicionales")
				);
				
				proveedores.add(proveedor);
				
			}
			
			respuesta.setRespuesta(proveedores);
			
		}  catch (Exception e) {
			respuesta = new Respuesta("Error al intentar obtener los proveedores", false, null);
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
	
	public Respuesta obtenerProveedorIdproveedor(String IdProveedor) {
		respuesta = new Respuesta("",true,null);
		query  = "select * from proveedores where Estatus = 'ACTIVO' and Id_Proveedor = '"+IdProveedor+"'";
		proveedor = null;
		try {		
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			while (resultados.next()) {
				
				proveedor = new Proveedor(
						resultados.getString("Id_Proveedor"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getString("Empresa"),
						resultados.getString("Direccion"),
						resultados.getDate("FechaRegistro"),
						resultados.getString("TipoProducto"),
						resultados.getString("NotasAdicionales")
				);
				
			}
			
			respuesta.setRespuesta(proveedor);
			
		}  catch (Exception e) {
			respuesta = new Respuesta("Error al intentar obtener los proveedores", false, null);
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
	
	public Respuesta obtenerProveedorTelefono(String Telefono) {
		
		respuesta = new Respuesta("",true,null);
		query  = "select * from proveedores where Estatus = 'ACTIVO' and Telefono = '"+Telefono+"'";
		proveedores = new ArrayList<Proveedor>();
		
		try {		
			proveedores.clear();
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			while (resultados.next()) {
				
				proveedor = new Proveedor(
						resultados.getString("Id_Proveedor"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getString("Empresa"),
						resultados.getString("Direccion"),
						resultados.getDate("FechaRegistro"),
						resultados.getString("TipoProducto"),
						resultados.getString("NotasAdicionales")
				);
				proveedores.add(proveedor);
				
			}
			
			respuesta.setRespuesta(proveedores);
			
		}  catch (Exception e) {
			respuesta = new Respuesta("Error al intentar obtener los proveedores", false, null);
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

	public Respuesta insertarProveedor(Proveedor proveedorR) {
		
		respuesta = new Respuesta("Proveedor Registrado Exitosamente",true,null);
		query = "insert into proveedores (Id_Proveedor,Nombre,Apaterno,Amaterno, Telefono, Correo, Empresa, Direccion,TipoProducto, NotasAdicionales, Estatus, FechaRegistro) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?);";
		
		
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			stm.setString(1, proveedorR.getId_Proveedor());
			stm.setString(2, proveedorR.getNombre());
			stm.setString(3, proveedorR.getApaterno());
			stm.setString(4, proveedorR.getAmaterno());
			stm.setString(5, proveedorR.getTelefono());
			stm.setString(6, proveedorR.getCorreo());
			stm.setString(7, proveedorR.getEmpresa());
			stm.setString(8, proveedorR.getDireccion());		
			stm.setString(9, proveedorR.getTipoProducto());
			stm.setString(10, proveedorR.getNotasAdicionales());
			stm.setString(11, "ACTIVO");
			stm.setDate(12,  proveedorR.getFechaRegistro());
			stm.execute();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar insertar Proveedor "+e.getMessage(),false,null);
		}finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return respuesta;
		
	}
	
	public Respuesta actualizarProveedor(Proveedor proveedorA) {
		
		respuesta = new Respuesta("Proveedor Actualizado Correctamente",true,null);
		query = "update proveedores set  Nombre=?, Apaterno=?, Amaterno=?, Telefono=?, Correo=?, Empresa=?, Direccion=?, FechaRegistro=?"
				+", TipoProducto =?, NotasAdicionales =? where Id_Proveedor = '"+proveedorA.getId_Proveedor()+"'";
		
		try {
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			
			stm.setString(1, proveedorA.getNombre());
			stm.setString(2, proveedorA.getApaterno());
			stm.setString(3, proveedorA.getAmaterno());
			stm.setString(4, proveedorA.getTelefono());
			stm.setString(5, proveedorA.getCorreo());
			stm.setString(6, proveedorA.getEmpresa());
			stm.setString(7, proveedorA.getDireccion());
			stm.setDate(8, proveedorA.getFechaRegistro());
			stm.setString(9, proveedorA.getTipoProducto());
			stm.setString(10, proveedorA.getNotasAdicionales());	
			stm.execute();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al Actualizar Producto "+e.getMessage(), false, null);
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
	
	public Respuesta eliminarProveedor(String IdProveedor) {
		
		respuesta = new Respuesta("Proveedor Eliminado Correctamente",true,null);
		query = "update proveedores set Estatus='INACTIVO' where Id_Proveedor = '"+IdProveedor+"'";
		
		try {
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			stm.execute();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al Eliminar Proveedor "+e.getMessage(), false, null);
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
	
	
//	public static void main(String[]args) {
//		ProveedoresDAO dao = new ProveedoresDAO();
//		Respuesta r = new Respuesta("",true,null);
//		
//		List <Proveedor> proveedores_ = new ArrayList<Proveedor>();
//		
//		r = dao.obtenerProveedores();
//		
//		proveedores_ =  (List<Proveedor>) r.getRespuesta();
//		
//		for  (Proveedor proveedor : proveedores_) {
//			System.out.println(proveedor.getNombre());
//		}
//		
//		proveedores_.clear();
//		r = dao.obtenerProveedorDescripcion("esp");
//		proveedores_ = (List<Proveedor>)  r.getRespuesta();
//		
//		for (Proveedor proveedor : proveedores_) {
//			System.out.println(proveedor.getNombre());
//		}
//		
//		java.util.Date utilDate = new java.util.Date();
//		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//		
//		Proveedor p = new Proveedor(
//				"9",
//			"Sarmiento",
//			"1",
//			"1",
//			"1",
//			"1",
//			"1",
//			"1",
//			sqlDate,
//			"",
//			""
//			);
//		
////		r = dao.insertarProveedor(p);
//		r = dao.actualizarProveedor(p);
//		
//		System.out.println(r.getMensaje());
//		
//		
//		
//	}
	
}
