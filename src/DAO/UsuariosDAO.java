package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import HerramientasConexion.ConexionGlobal;
import Models.Producto;
import Models.Respuesta;
import Models.Usuario;

public class UsuariosDAO {
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Usuario usuario =null;
	List<Usuario> usuarios = null;;
	String query ="";
	
	public UsuariosDAO() {
		usuarios = new ArrayList<Usuario>();
	}
	
	public Respuesta obtenerUsuarios() {
		
		respuesta = new Respuesta("Usuarios Cargados Correctamente",true,null);
		query = "select * from usuarios";
		
		try {
			
            ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
            resultados =  stm.executeQuery();           
            usuarios.clear();
            
            while (resultados.next()) {                
                usuario = new Usuario(
                		resultados.getInt("Id_Usuario"),
                		resultados.getString("usuario"),
                		resultados.getString("password"),
                		resultados.getString("nombre"),
		                resultados.getString("apaterno"),
		                resultados.getString("amaterno"),
		                resultados.getString("correo"),
		                resultados.getString("direccion"), 
		                resultados.getString("puesto"),
		                resultados.getString("telefono")
                );
                usuarios.add(usuario);
            }   
            
            respuesta.setRespuesta(usuarios);
			
		} catch (SQLException e) {
			// TODO: handle exception
			respuesta = new Respuesta("Error al intentar obtener a los usuarios "+e.getMessage(), false, null);
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
	
	public Respuesta insertarUsuario(Usuario usuarioR) {
		
		respuesta = new Respuesta("Usuario insertado Correctamente",true,null);
		query = "insert into Usuarios(usuario,password,nombre,apaterno,amaterno,correo,direccion,puesto,telefono)"
				+"values(?,?,?,?,?,?,?,?,?)";
		
		try {
			
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			
            stm.setString(1,usuarioR.getUsuario());
            stm.setString(2,usuarioR.getPassword());
            stm.setString(3,usuarioR.getNombre());
            stm.setString(4,usuarioR.getApaterno());
            stm.setString(5,usuarioR.getAmaterno());
            stm.setString(6,usuarioR.getCorreo());
            stm.setString(7,usuarioR.getDireccion());
            stm.setString(8,usuarioR.getPuesto());
            stm.setString(9,usuarioR.getTelefono());
            
            stm.execute();

			
		}catch (Exception e) {
			respuesta = new Respuesta("Error al Registrar Porducto. "+e.getStackTrace(), false, null);
			e.printStackTrace();
			System.out.println(e.getMessage());
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
	
	public Respuesta actualizarUsuario(Usuario usuarioR) {
		
		respuesta = new Respuesta("Producto Registrado Correctamente.",true,null);
		
		String query = "update Usuarios set usuario=?, password=?, nombre=?, apaterno=?, amaterno=?, correo=?, direccion=?, puesto=?, telefono=?"
				+ "	where Id_Usuario=? ;";
		
		try {
			
			ConexionGlobal.establecerConexio();	
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			
            stm.setString(1,usuarioR.getUsuario());
            stm.setString(2,usuarioR.getPassword());
            stm.setString(3,usuarioR.getNombre());
            stm.setString(4,usuarioR.getApaterno());
            stm.setString(5,usuarioR.getAmaterno());
            stm.setString(6,usuarioR.getCorreo());
            stm.setString(7,usuarioR.getDireccion());
            stm.setString(8,usuarioR.getPuesto());
            stm.setString(9,usuarioR.getTelefono());
            stm.setInt(10,usuarioR.getId_Usuario());
            
            stm.execute();
			
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al Registrar al Usuario. "+e.getStackTrace(), false, null);
			e.printStackTrace();
			System.out.println(e.getMessage());
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
	
	public Respuesta eliminarUsuario(int idUsuario) {
		
		respuesta = new Respuesta("Usuario Eliminado Correctamente.",true,null);
		query = "delete from usuarios where Id_Usuario = "+idUsuario;
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			stm.execute();
			
		} catch (Exception e) {
			respuesta = new Respuesta("Error al Intentar Eliminar al Usuario. "+e.getStackTrace(), false, null);
			e.printStackTrace();
			System.out.println(e.getMessage());
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
		
		
		UsuariosDAO dao = new UsuariosDAO();
		
		Respuesta r = new Respuesta("",true,null);
		
		r = dao.obtenerUsuarios();
		
		List<Usuario> usuarios = (List<Usuario> ) r.getRespuesta();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getNombre());
		}
		
		Usuario u = new Usuario(
			2,
			"a",
			"a",
			"ao",
			"a",
			"a",
			"a",
			"a",
			"a",
			"a"
						);
		
//		r = dao.insertarUsuario(u);
//		r = dao.actualizarUsuario(u);
		r = dao.eliminarUsuario(2);
		
	}
	
}
