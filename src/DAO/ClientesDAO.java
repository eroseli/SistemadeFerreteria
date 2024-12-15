package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Proveedor;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class ClientesDAO {
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Cliente cliente =null;	
	List<Cliente> clientes = null;
	String query = "";
	
	public ClientesDAO() {
		
	}
	
	public Respuesta obtenerCantidadClientes() {

		respuesta = new Respuesta("",true,null);
		query = "select COUNT(Id_Cliente) Cantidad from clientes";
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
	
	public Respuesta obtenerClientes() {
		
		respuesta = new Respuesta("",true,null);
		cliente = new Cliente();
		query = "select * from clientes WHERE Estatus = 'ACTIVO';";
		clientes = new ArrayList<Cliente>();

		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {
				
				cliente = new Cliente(
						resultados.getString("Id_Cliente"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getDate("FechaNac"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getInt("Compras"),
						resultados.getDate("FechaRegistro")
						);
				clientes.add(cliente);
			}
			
			respuesta.setRespuesta(clientes);
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
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
	
	public Respuesta obtenerCliente(String identificador, String telefono) {
		respuesta = new Respuesta("",true,null);
		query  = "select * from clientes where Estatus = 'ACTIVO' and ( Id_Cliente = '"+identificador+"' or telefono = '"+telefono+"' )";
		clientes = new ArrayList<Cliente>();
		try {
			
			clientes.clear();
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				cliente = new Cliente(
						resultados.getString("Id_Cliente"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getDate("FechaNac"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getInt("Compras"),
						resultados.getDate("FechaRegistro")				
				);
				
			}
			
			respuesta.setRespuesta(cliente);
			
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
	
	public Respuesta obtenerClienteDescripcion(String descripcionNombre) {
		
		
		respuesta = new Respuesta("",true,null);
		query  = "select * from clientes where (CONCAT(Nombre,\" \",Apaterno,\" \",\" \",Amaterno) like '%"+descripcionNombre+"%' OR Telefono like '%"+descripcionNombre+ "%' ) AND Estatus = 'ACTIVO'";
		clientes = new ArrayList<Cliente>();
		
		try {
			
			clientes.clear();
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				cliente = new Cliente(
						resultados.getString("Id_Cliente"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getDate("FechaNac"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getInt("Compras"),
						resultados.getDate("FechaRegistro")					
				);
				
				clientes.add(cliente);
				
			}
			
			respuesta.setRespuesta(clientes);
			
		}  catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes", false, null);
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
	
	public Respuesta insertarCliente(Cliente clienteR) {
		

		respuesta = new Respuesta("Cliente Registrado Exitosamente",true,null);
		query = "insert into clientes(Id_Cliente, Nombre, Apaterno, Amaterno, FechaNac, Telefono, Correo, Compras, Estatus,FechaRegistro) "
				+ " values(?,?,?,?,?,?,?,?,?,?);";
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			stm.setString(1, clienteR.getIdentificador());
			stm.setString(2, clienteR.getNombre());
			stm.setString(3, clienteR.getApaterno());
			stm.setString(4, clienteR.getAmaterno());
			stm.setDate(5, clienteR.getFechaNac());
			stm.setString(6, clienteR.getTelefono());
			stm.setString(7, clienteR.getCorreo());
			stm.setInt(8, clienteR.getCompras());
			stm.setString(9, "ACTIVO");
			stm.setDate(10, Date.valueOf(LocalDate.now()));
			stm.execute();
			
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al insertar un Cliente"+e.getMessage(), false, null);
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
	
	public Respuesta actualizarCliente(Cliente clienteA) {
		
		respuesta = new Respuesta("Cliente Actualizado Correctamente",true,null);
		query = "update clientes set  Nombre=?, Apaterno=?, Amaterno=?, FechaNac=?, Telefono=?, Correo=?, Compras=?"
				+" where Id_Cliente = '"+clienteA.getIdentificador()+"'";
		
		try {
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			stm.setString(1, clienteA.getNombre());
			stm.setString(2, clienteA.getApaterno());
			stm.setString(3, clienteA.getAmaterno());
			stm.setDate(4, clienteA.getFechaNac());
			stm.setString(5, clienteA.getTelefono());
			stm.setString(6, clienteA.getCorreo());
			stm.setInt(7, clienteA.getCompras());
			stm.execute();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al Actualizar al Cliente "+e.getMessage(), false, null);
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
	
	public Respuesta eliminarCliente(String Id_Cliente) {
		
		respuesta = new Respuesta("Cliente Eliminado Correctamente.",true,null);
		query = "update clientes set Estatus = 'INACTIVO' where Id_Cliente = '"+Id_Cliente+"';";
		
		try {
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			stm.execute();
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al Eliminar Cliente "+Id_Cliente, false, null);
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
	
		Respuesta r = new Respuesta("",true,null);
		
		ClientesDAO dao = new ClientesDAO();
		
		List<Cliente>clientes = new ArrayList<Cliente>();
		
		r = dao.obtenerClientes();
		clientes = (List<Cliente>)r.getRespuesta();
		
		
		for (Cliente cliente : clientes) {
			System.out.println(cliente.getNombre());
		}
		
		clientes.clear();
		
		r = dao.obtenerClienteDescripcion("er");
		clientes = (List<Cliente>)r.getRespuesta();
		
		for (Cliente cliente : clientes) {
			System.out.println(cliente.getNombre());
		}
		
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		Cliente c = new Cliente(
				"00000",
				"Damian",
				"damina",
				"damian",
				sqlDate,
				"0",
				"0",
				0,
				null
				);
		//r = dao.insertarCliente(c);
		//r = dao.actualizarCliente(c);
		r = dao.eliminarCliente("");
		System.out.println(r.getMensaje());
		
		
	}
	

}
