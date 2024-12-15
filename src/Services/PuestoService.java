package Services;

import DAO.PuestosDAO;
import HerramientasConexion.Errors;
import HerramientasConexion.Herramientas;
import Models.Respuesta;

public class PuestoService {
	
	Respuesta respuesta = null;
	
	public Respuesta obtenerPuesto() {
		
		respuesta = new Respuesta("",true,null);
		PuestosDAO puestosDAO = new PuestosDAO();		
		return puestosDAO.obtenerMarcas();
		
	}

}
