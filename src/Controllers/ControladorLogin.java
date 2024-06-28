package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.Respuesta;
import Models.Usuario;

public class ControladorLogin {
	
	Connection con = null;
    PreparedStatement stm = null;
    ResultSet resultados = null;
    
    public ControladorLogin(Connection con) {
    	this.con = con;
    }
    

	public Respuesta validarUsuario(String username, String password) {
		
		Usuario usuario = new Usuario();
		Respuesta respuesta = new Respuesta("Usuario registrado Correctamente",true,null);
		
		try {
            String consulta = "select * from usuarios where usuario = '"+username+"' and password = '"+password+"';";
            stm =  (PreparedStatement) con.prepareStatement(consulta);
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
		                resultados.getString("telefono")
                );
                respuesta.setRespuesta(usuario);
                return respuesta;
            }
            
            respuesta = new Respuesta("Usuario No encontrado",false,null);
            
        } catch (Exception e) {
        	respuesta = new Respuesta("Usuario no encontrado :"+e.getMessage(),false, null);
        }
		return respuesta;
						
	}
	
	

}
