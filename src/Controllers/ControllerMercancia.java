package Controllers;

import DAO.MercanciaDAO;
import DAO.ModelsDAO.REGISTROMERCANCIA;
import Models.Respuesta;

public class ControllerMercancia {

	
	public static void main(String[]args) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		MercanciaDAO dao = new MercanciaDAO(); 
		REGISTROMERCANCIA registromercancia = new REGISTROMERCANCIA(1,1,2,240,"ENTRADA",120);
		
		respuesta = dao.registroMercacia(registromercancia);
				
		System.out.println(respuesta.getMensaje());
		
	}
	
	
}
