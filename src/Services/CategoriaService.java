package Services;

import DAO.CategoriasDAO;
import DAO.ModelsDAO.Categoria;
import HerramientasConexion.Errors;
import Models.Respuesta;

public class CategoriaService {
	
	
	Respuesta respuesta = null;
	
	public Respuesta obtenerCategorias() {
		
		respuesta = new Respuesta("",true,null);
		CategoriasDAO categoriasDAO = new CategoriasDAO();
		return categoriasDAO.obtenerCategorias();
	}
	
	public Respuesta InsertarCategoria(Categoria categoria) {
		
		respuesta =  new Respuesta("",true,null);
		CategoriasDAO categoriasDAO = new CategoriasDAO();
		
		respuesta =  categoriasDAO.insertarCategoria(categoria);
		
		if (!respuesta.getValor()) {
			respuesta.setMensaje(Errors.errors((Integer) respuesta.getRespuesta()));
		}
		
		return respuesta;
		
	}
	
	public Respuesta eliminarCategoria(int idCategoria) {
		
		respuesta = new Respuesta("",true,null);
		CategoriasDAO categoriasDAO =new CategoriasDAO();
		return categoriasDAO.eliminarCategoria(idCategoria);
		
	}

}
