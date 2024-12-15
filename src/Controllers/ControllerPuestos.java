package Controllers;

import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Services.PuestoService;

public class ControllerPuestos {
	
	private Respuesta respuesta =null;
	private PuestoService puestoService = null;
	
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
puestoService =  new PuestoService();
				
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.seleccionar:
			respuesta = puestoService.obtenerPuesto();
			break;
		}		
		
		return respuesta;
		
	}

}
