package Controllers;

import java.sql.ResultSet;

import DAO.LoginDAO;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class ControllerLogin {
	
	Respuesta respuesta;
	
    public Respuesta AccesoUsuario(String usuario, String password) {
    	
    	LoginDAO loginDAO = new LoginDAO();
    	respuesta = new Respuesta("",true,null);
    	
    	System.out.println(usuario+" "+password);
    	
    	respuesta = loginDAO.validarUsuario(usuario, password);
    	
    	return respuesta;
    }

}
