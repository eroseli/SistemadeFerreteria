package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import HerramientasConexion.ConexionGlobal;
import Models.Producto;
import Models.Proveedor;
import Models.Respuesta;

public class ProveedoresDAO {
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Proveedor proveedor =null;	
	List<Proveedor> proveedores = null;
	String query = "";
	
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
						resultados.getInt("Id_Proveedor"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Num_Telefono"),
						resultados.getString("Correo"),
						resultados.getString("Empresa"),
						resultados.getString("Direccion"),
						resultados.getDate("FechaRegistro")
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
	
	public Respuesta obtenerProductosDescripcion(String descripcionNombre) {
		
		respuesta = new Respuesta("",true,null);
		query  = "select * from proveedores where nombre like '%"+descripcionNombre+"%'";
		proveedores = new ArrayList<Proveedor>();
		
		try {
			
			proveedores.clear();
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				proveedor = new Proveedor(
						resultados.getInt("Id_Proveedor"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Num_Telefono"),
						resultados.getString("Correo"),
						resultados.getString("Empresa"),
						resultados.getString("Direccion"),
						resultados.getDate("FechaRegistro")						
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
		query = "insert into Proveedores (Nombre,Apaterno,Amaterno, Num_Telefono, Correo, Empresa, Direccion) "
				+ " values(?,?,?,?,?,?,?);";
		
		
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			stm.setString(1, proveedorR.getNombre());
			stm.setString(2, proveedor.getApaterno());
			stm.setString(3, proveedor.getAmaterno());
			stm.setString(4, proveedor.getNum_Telefono());
			stm.setString(5, proveedorR.getCorreo());
			stm.setString(6, proveedorR.getEmpresa());
			stm.setString(7, proveedor.getDireccion());
			
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
		query = "update proveedores set  Nombre=?, Apaterno=?, Amaterno=?, Num_Telefono=?, Correo=?, Empresa=?, Direccion=?, FechaRegistro=?"
				+" where Id_Proveedor ="+proveedorA.getId_Proveedor();
		
		try {
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			
			stm.setString(1, proveedorA.getNombre());
			stm.setString(2, proveedorA.getApaterno());
			stm.setString(3, proveedorA.getAmaterno());
			stm.setString(4, proveedorA.getNum_Telefono());
			stm.setString(5, proveedorA.getCorreo());
			stm.setString(6, proveedorA.getEmpresa());
			stm.setString(7, proveedorA.getDireccion());
			stm.setDate(8, proveedorA.getFechaRegistro());
			
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
	
	public static void main(String[]args) {
		ProveedoresDAO dao = new ProveedoresDAO();
		Respuesta r = new Respuesta("",true,null);
		
		List <Proveedor> proveedores_ = new ArrayList<Proveedor>();
		
		r = dao.obtenerProveedores();
		
		proveedores_ =  (List<Proveedor>) r.getRespuesta();
		
		for  (Proveedor proveedor : proveedores_) {
			System.out.println(proveedor.getNombre());
		}
		
		proveedores_.clear();
		r = dao.obtenerProductosDescripcion("esp");
		proveedores_ = (List<Proveedor>)  r.getRespuesta();
		
		for (Proveedor proveedor : proveedores_) {
			System.out.println(proveedor.getNombre());
		}
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		Proveedor p = new Proveedor(
				9,
			"Sarmiento",
			"1",
			"1",
			"1",
			"1",
			"1",
			"1",
			sqlDate
				);
		
//		r = dao.insertarProveedor(p);
		r = dao.actualizarProveedor(p);
		
		System.out.println(r.getMensaje());
		
		
		
	}
	
}
