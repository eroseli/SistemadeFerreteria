package Controllers;

import HerramientasConexion.Herramientas;
import Models.ClienteView;
import Models.Respuesta;
import Models.UsuarioView;
import Services.ClienteService;
import Services.UsuarioService;

public class ControllerCliente {

	
	private Respuesta respuesta = null;
	private ClienteService clienteService = null;
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		clienteService = new ClienteService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:
			respuesta = clienteService.insertar((ClienteView) objeto);
			break;
		case Herramientas.tipoOperacion.actualizar:
			respuesta = clienteService.actualizar((ClienteView) objeto);
			break;
		case Herramientas.tipoOperacion.eliminar:
			String idCliente =  ((String) objeto);
			respuesta = clienteService.eliminar(idCliente);
			break;
		case Herramientas.tipoOperacion.seleccionar:
			respuesta = clienteService.seleccionar((String)objeto);
			break;
		}
		
		return respuesta;
	}
	
}
