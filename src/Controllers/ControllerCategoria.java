package Controllers;

import DAO.ModelsDAO.Categoria;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Services.CategoriaService;

public class ControllerCategoria {

	private Respuesta respuesta = null;
	private CategoriaService categoriaService= null;
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		
		categoriaService = new   CategoriaService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:	
			Categoria categoria =  (Categoria) objeto;
			respuesta = categoriaService.InsertarCategoria(categoria);
			break;
		case Herramientas.tipoOperacion.actualizar:		
			break;
		case Herramientas.tipoOperacion.eliminar:	
			respuesta = categoriaService.eliminarCategoria((Integer) objeto);
			break;
		case Herramientas.tipoOperacion.seleccionar:	
			respuesta = categoriaService.obtenerCategorias();
			break;

		default:
			respuesta = new Respuesta("Opción no Válida.",false,null);
			break;
		}
			
		return respuesta;
		
	}
	
	
}
