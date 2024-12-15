package Controllers;

import HerramientasConexion.Herramientas;
import Models.ProductoBusquedaView;
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
			respuesta = productoService.actualizar((ProductoView) objeto);
			break;
		case Herramientas.tipoOperacion.eliminar:
			String codigo =  (String) objeto;
			respuesta = productoService.eliminar(codigo);
			break;
		case Herramientas.tipoOperacion.seleccionar:
			ProductoBusquedaView busquedaView = (ProductoBusquedaView) objeto;
			respuesta = productoService.seleccionar(busquedaView);
			break;
		case 5:
			String producto = (String) objeto;
			respuesta = productoService.obtenerProductoCoincidencia(producto);
			break;
		}
		return respuesta;
	}
	
}
