package Controllers;

import HerramientasConexion.Herramientas;
import Models.ProductoView;
import Models.Respuesta;
import Models.UsuarioView;
import Services.UsuarioService;

public class ControllerUsuario {

	private Respuesta respuesta = null;
	private UsuarioService usuarioService = null;
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		usuarioService = new UsuarioService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:
			respuesta = usuarioService.insertar((UsuarioView) objeto);
			break;
		case Herramientas.tipoOperacion.actualizar:
			respuesta = usuarioService.actualizar((UsuarioView) objeto);
			break;
		case Herramientas.tipoOperacion.eliminar:
			UsuarioView usuarioView =  ((UsuarioView) objeto);
			respuesta = usuarioService.eliminar(usuarioView.getId_Usuario());
			break;
		case Herramientas.tipoOperacion.seleccionar:
			String nombre = (String) objeto;
			respuesta = usuarioService.selecciona(nombre);
			break;

		}
		
		return respuesta;
	}
	
}
