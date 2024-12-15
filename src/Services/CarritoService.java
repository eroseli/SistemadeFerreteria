package Services;

import DAO.ProductosDAO;
import Models.Respuesta;

public class CarritoService {

	Respuesta respuesta = null;
	
	public Respuesta eliminarCarrito(int idCarrito) {
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaC = new Respuesta("",true,null);
		ProductosDAO dao = new ProductosDAO();

		respuesta = dao.eliminarCarritoDet(idCarrito);	
		if(!respuesta.getValor())
			return respuesta;
		
		respuesta =  dao.eliminarCarrito( idCarrito);
		
		return respuesta;	
	}
	
}
