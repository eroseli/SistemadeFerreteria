package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DAO.ModelsDAO.Usuario;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class LoginDAO {
	
	PreparedStatement stm = null;
    ResultSet resultados = null;
    
	public Respuesta validarUsuario(String username, String password) {
		
		Usuario usuario = new Usuario();
		Respuesta respuesta = new Respuesta("Usuario registrado Correctamente",true,null);
		
		try {
            String consulta = "select * from usuarios where usuario = '"+username+"' and password = '"+password+"';";
            ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(consulta);           
            resultados =  stm.executeQuery();           
            
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
		                resultados.getString("telefono"),
		                resultados.getDate("fechaRegistro")

                );
                respuesta.setRespuesta(usuario);
                ConexionGlobal.cerrarConexion();
                return respuesta;
            }
            
            ConexionGlobal.cerrarConexion();
            
            
            
            respuesta = new Respuesta("Usuario No encontrado",false,null);
            
        } catch (Exception e) {
        	respuesta = new Respuesta("Usuario no encontrado :"+e.getMessage(),false, null);
        }
		return respuesta;
						
	}

}
