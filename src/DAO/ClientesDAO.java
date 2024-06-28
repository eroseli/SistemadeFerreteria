package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import HerramientasConexion.ConexionGlobal;
import Models.Cliente;
import Models.Proveedor;
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
	
	
	public Respuesta obtenerClientes() {
		
		respuesta = new Respuesta("",true,null);
		cliente = new Cliente();
		query = "select * from clientes;";
		clientes = new ArrayList<Cliente>();

		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados  = stm.executeQuery();
			
			while(resultados.next()) {
				
				cliente = new Cliente(
						resultados.getInt("Id_Cliente"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Identificador"),
						resultados.getDate("FechaNac"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getInt("Compras")
						);
				clientes.add(cliente);
			}
			
			respuesta.setRespuesta(clientes);
			
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
	
	public Respuesta obtenerClienteDescripcion(String descripcionNombre) {
		
		
		respuesta = new Respuesta("",true,null);
		query  = "select * from clientes where nombre like '%"+descripcionNombre+"%'";
		clientes = new ArrayList<Cliente>();
		
		try {
			
			clientes.clear();
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				cliente = new Cliente(
						resultados.getInt("Id_Cliente"),
						resultados.getString("Nombre"),
						resultados.getString("Apaterno"),
						resultados.getString("Amaterno"),
						resultados.getString("Identificador"),
						resultados.getDate("FechaNac"),
						resultados.getString("Telefono"),
						resultados.getString("Correo"),
						resultados.getInt("Compras")					
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
		query = "insert into Clientes (Nombre, Apaterno, Amaterno, Identificador, FechaNac, Telefono, Correo, Compras) "
				+ " values(?,?,?,?,?,?,?,?);";
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			stm.setString(1, clienteR.getNombre());
			stm.setString(2, clienteR.getApaterno());
			stm.setString(3, clienteR.getAmaterno());
			stm.setString(4, clienteR.getIdentificador());
			stm.setDate(5, clienteR.getFechaNac());
			stm.setString(6, clienteR.getTelefono());
			stm.setString(7, clienteR.getCorreo());
			stm.setInt(8, clienteR.getCompras());
			
			stm.execute();
			
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al insertar un Cliente", false, null);
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
		query = "update clientes set  Nombre=?, Apaterno=?, Amaterno=?, Identificador=?, FechaNac=?, Telefono=?, Correo=?, Compras=?"
				+" where Id_Cliente ="+clienteA.getId_Cliente();
		
		try {
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			stm.setString(1, clienteA.getNombre());
			stm.setString(2, clienteA.getApaterno());
			stm.setString(3, clienteA.getAmaterno());
			stm.setString(4, clienteA.getIdentificador());
			stm.setDate(5, clienteA.getFechaNac());
			stm.setString(6, clienteA.getTelefono());
			stm.setString(7, clienteA.getCorreo());
			stm.setInt(8, clienteA.getCompras());
			
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
	
	public Respuesta eliminarCiente(int idCliente) {
		
		respuesta = new Respuesta("Cliente Eliminado Correctamente.",true,null);
		query = "delete from clientes where Id_Cliente = "+idCliente+" ;";
		
		try {
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			stm.execute();
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al Eliminar Cliente "+idCliente, false, null);
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
				12,
				"Damian",
				"damina",
				"damian",
				"00000",
				sqlDate,
				"0",
				"0",
				0
				);
		//r = dao.insertarCliente(c);
		//r = dao.actualizarCliente(c);
		r = dao.eliminarCiente(12);
		System.out.println(r.getMensaje());
		
		
	}
	

}
