package Controllers;

import DAO.MercanciaDAO;
import DAO.ModelsDAO.REGISTROMERCANCIA;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Models.UsuarioView;
import Services.MercanciaService;
import Services.UsuarioService;

public class ControllerMercancia {

	private Respuesta respuesta = null;
	private MercanciaService mercanciaService = null;
	
	public static void main(String[]args) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		MercanciaDAO dao = new MercanciaDAO(); 
		REGISTROMERCANCIA registromercancia = new REGISTROMERCANCIA(1,1,2,240,"ENTRADA",120);
		
		respuesta = dao.registroMercacia(registromercancia);
				
		System.out.println(respuesta.getMensaje());
		
	}
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		mercanciaService = new MercanciaService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:
			respuesta = mercanciaService.insertar(objeto);
			break;
		case Herramientas.tipoOperacion.actualizar:
			respuesta = mercanciaService.actualizar((DAO.ModelsDAO.MercanciaDAO) objeto);
			break;
		case Herramientas.tipoOperacion.eliminar:
			int idMercancia =  ((Integer) objeto);
			respuesta = mercanciaService.eliminar(idMercancia);
			break;
		}
		
		
		return respuesta;
		
	}
	
	
}
