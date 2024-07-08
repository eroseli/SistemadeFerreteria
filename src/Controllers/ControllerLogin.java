package Controllers;

import java.sql.ResultSet;

import DAO.LoginDAO;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class ControllerLogin {
	
	Respuesta respuesta;
	
    public Respuesta AccesoUsuario(String usuairo, String password) {
    	
    	LoginDAO loginDAO = new LoginDAO();
    	respuesta = new Respuesta("",true,null);
    	
    	
    	respuesta = loginDAO.validarUsuario(usuairo, password);
    	
    	return respuesta;
    }

}
