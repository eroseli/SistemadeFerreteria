package Services;

import DAO.MarcasDAO;
import DAO.ModelsDAO.MarcaDAO;
import HerramientasConexion.Errors;
import Models.Respuesta;

public class MarcaService {

	Respuesta respuesta =null;	
	
	public Respuesta obtenerMarcas() {
		
		respuesta =  new Respuesta("",true,null);
		MarcasDAO marcasDAO = new MarcasDAO();
		return marcasDAO.obtenerMarcas();
		
	}
	
	public Respuesta insertarMarca(MarcaDAO marcaDao) {
		
		respuesta =  new Respuesta("",true,null);
		MarcasDAO marcasDAO = new MarcasDAO();
		respuesta =  marcasDAO.insertarMarca(marcaDao);
		
		if (!respuesta.getValor()) {
			respuesta.setMensaje(Errors.errors( (Integer) respuesta.getRespuesta()));
		}
		
		return respuesta;
		
	}
	
	public Respuesta eliminar(int idMarca) {
		
		respuesta = new Respuesta("",true,null);
		MarcasDAO marcaDAO = new MarcasDAO();
		return marcaDAO.eliminarMarca(idMarca);
	}
	
}
