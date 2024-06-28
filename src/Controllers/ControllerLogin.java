package Controllers;

import java.sql.ResultSet;

import DAO.LoginDAO;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;
import Models.Usuario;

public class ControllerLogin {
	
	Respuesta respuesta;
	
    public Respuesta AccesoUsuario(String usuairo, String password) {
    	
    	LoginDAO loginDAO = new LoginDAO();
    	respuesta = new Respuesta("",true,null);
    	
    	
    	respuesta = loginDAO.validarUsuario(usuairo, password);
    	
    	return respuesta;
    }

}
