package Controllers;

import HerramientasConexion.Herramientas;
import Models.ProveedorView;
import Models.Respuesta;
import Services.ProveedorService;

public class ControllerProveedor {
	
	private Respuesta respuesta = null;
	private ProveedorService proveedorService = null;
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		proveedorService = new ProveedorService();
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:
			respuesta = proveedorService.insertar((ProveedorView) objeto);
			break;
		case Herramientas.tipoOperacion.actualizar:
			respuesta = proveedorService.actualizar((ProveedorView) objeto);
			break;
		case Herramientas.tipoOperacion.eliminar:
			String idProveedor = (String) objeto;
			respuesta = proveedorService.eliminar((idProveedor));
			break;
		case Herramientas.tipoOperacion.seleccionar:
			respuesta = proveedorService.seleccionar((String)objeto);
			break;
		}
		return respuesta;
	}
	
}
