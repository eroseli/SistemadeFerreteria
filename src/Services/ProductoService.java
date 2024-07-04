package Services;

import Models.ProductoView;
import Models.Respuesta;

public class ProductoService {

	private Respuesta respuesta;
	
	public void prueba() {
		
	}
	
	
	public Respuesta insertar(ProductoView productoView) {
		
		respuesta = new Respuesta("",false,productoView);
		
		
		
		return respuesta;
	}
	
	public Respuesta validarProducto() {
		
		
		
		
		return respuesta;
		
	}
	
}
