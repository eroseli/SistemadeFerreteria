package Controllers;

import DAO.ModelsDAO.Categoria;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Services.CarritoService;
import Services.CategoriaService;

public class ControllerCarrito {

	
	private Respuesta respuesta = null;
	private CarritoService carritoService= null;
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
			
		carritoService = new   CarritoService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:	
			break;
		case Herramientas.tipoOperacion.actualizar:		
			break;
		case Herramientas.tipoOperacion.eliminar:
			respuesta = carritoService.eliminarCarrito((Integer) objeto);
			break;
		case Herramientas.tipoOperacion.seleccionar:	
			break;

		default:
			respuesta = new Respuesta("Opción no Válida.",false,null);
			break;
		}
			
		return respuesta;
		
	}
	
}
