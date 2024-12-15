package Controllers;

import DAO.ModelsDAO.MarcaDAO;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Services.MarcaService;

public class ControllerMarca {
	
	private Respuesta respuesta = null;
	private MarcaService marcaService = null;
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		marcaService = new MarcaService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:		
			MarcaDAO marcaDAO = (MarcaDAO) objeto;
			respuesta =  marcaService.insertarMarca(marcaDAO);
			break;
		case Herramientas.tipoOperacion.actualizar:		
			break;
		case Herramientas.tipoOperacion.eliminar:	
			respuesta = marcaService.eliminar((Integer) objeto);
			break;
		case Herramientas.tipoOperacion.seleccionar:	
			respuesta = marcaService.obtenerMarcas();
			break;

		default:
			respuesta = new Respuesta("Opción no Válida.",false,null);
			break;
		}
		
		return respuesta;
	}

}
