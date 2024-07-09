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
			ClienteView clienteView =  ((ClienteView) objeto);
			respuesta = clienteService.eliminar(clienteView.getId_Cliente());
			break;
		case Herramientas.tipoOperacion.seleccionar:
			break;
		}
		
		return respuesta;
	}
	
}
