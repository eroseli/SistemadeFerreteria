package Controllers;

import HerramientasConexion.Herramientas;
import Models.ProductoView;
import Models.Respuesta;
import Services.ProductoService;

public class ControllerProducto {

	private Respuesta respuesta = null;
	private ProductoService productoService = null;
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		productoService = new ProductoService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:
			respuesta = productoService.insertar((ProductoView) objeto);
			break;
		case Herramientas.tipoOperacion.actualizar:
			break;
		case Herramientas.tipoOperacion.eliminar:
			break;
		case Herramientas.tipoOperacion.seleccionar:
			break;

		}
		
		
		return respuesta;
	}
	
}
