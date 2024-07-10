package Services;

import java.awt.image.RescaleOp;

import Models.ProveedorView;
import Models.Respuesta;

public class ProveedorService {

	Respuesta respuesta;
	
	public Respuesta insertar(ProveedorView proveedorView) {
		respuesta = new Respuesta("",true, null);
		return respuesta;
	}
	
	public Respuesta actualizar(ProveedorView proveedorView) {
		respuesta = new Respuesta("",true, null);
		return respuesta;
	}
	
	public Respuesta eliminar(String IdProveedor) {
		respuesta = new Respuesta("",true, null);
		return respuesta;
	}
	
}
