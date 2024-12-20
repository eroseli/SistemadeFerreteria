package Services;

import java.beans.PropertyChangeSupport;

import DAO.ProductosDAO;
import DAO.ModelsDAO.Producto;
import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.tipoOperacion;
import Models.ProductoBusquedaView;
import Models.ProductoView;
import Models.Respuesta;

public class ProductoService {

	private Respuesta respuesta;
	Producto p = null;
	
	public Respuesta insertar(ProductoView productoView) {
		ProductosDAO productoDao = new ProductosDAO();
		
		respuesta = new Respuesta("",false,productoView);
		Respuesta valida = new Respuesta("",true,null);
		
		respuesta = validarProducto(Herramientas.tipoOperacion.insertar,productoView);
		if(!respuesta.getValor())
			return respuesta;
		
		respuesta = TraducirDatos(productoView);
		if (!respuesta.getValor()) {
			return respuesta;
		}
		
		Producto pro = (Producto) respuesta.getRespuesta();		
		System.out.println("Codig"+pro.getCodigo());
		valida = productoDao.validarProductoEliminado( pro.getCodigo() );		
				
		if ( (Boolean) valida.getRespuesta()) {
			respuesta = realizarActualizacion(pro);
		}
		else {
			respuesta = realizarInsercion(pro);
		}
		
		return respuesta;
	}
	
	public Respuesta realizarInsercion(Producto producto) {
		respuesta = new Respuesta("",true,null);
		ProductosDAO productoDao = new ProductosDAO();
		
		respuesta = productoDao.insertarProducto(producto);
		return respuesta;
	}
	
	public Respuesta actualizar(ProductoView productoView) {

		respuesta = new Respuesta("",false,productoView);

		respuesta = validarProducto(Herramientas.tipoOperacion.actualizar, productoView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = TraducirDatos(productoView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = realizarActualizacion((Producto) respuesta.getRespuesta());
		return respuesta;
	}
	
	public Respuesta realizarActualizacion(Producto producto) {
		respuesta = new Respuesta("",true,null);
		ProductosDAO productoDao = new ProductosDAO();
		
		respuesta = productoDao.actualizarProducto(producto);
		return respuesta;
	}
	
	public Respuesta eliminar(String codigo) {
		
		respuesta = new Respuesta("",false,null);
		Respuesta respuestaT = new Respuesta("",false,null);
		
		ProductosDAO productosDAO = new ProductosDAO();
		respuesta = productosDAO.eliminarProducto(codigo);
		
		respuestaT = productosDAO.obtenerProductoCodigo(codigo);

		if ( !(respuestaT.getRespuesta() == null) )
			return new Respuesta("Problemas al intentar Eliminar el producto "+codigo,false,null);
		
		return respuesta;
	}
	
	public Respuesta seleccionar(ProductoBusquedaView productoBusquedaView) {
		respuesta = new Respuesta("",true,null);
		ProductosDAO productosDAO = new ProductosDAO();
		try {
			respuesta =  productosDAO.obtenerProductoBusqueda(productoBusquedaView);
		} catch (Exception e) {
			return new Respuesta("Problemas al intentar obtener los Productos "+e.getMessage(),false,null);
		}
		return respuesta;
	}
	
	public Respuesta obtenerProductoCoincidencia(String producto) {
		respuesta = new Respuesta("",true,null);
		ProductosDAO productosDAO = new ProductosDAO(); 
		try {
			respuesta = productosDAO.obtenerProductoCoincidencia(producto);
		} catch (Exception e) {
			return new Respuesta("Problemas al intentar obtener los Productos." +e.getMessage(),false,null);
		}
		return respuesta;
	}
	
	public Respuesta TraducirDatos(ProductoView productoView) {
	
		respuesta = new Respuesta("",true,null);
		
		try {
			
			Producto producto = new Producto();
			
			producto.setCodigo(productoView.getCodigo());
			producto.setNombre(productoView.getNombre());
			producto.setDescripcion(productoView.getDescripcion());
			producto.setCantidad(productoView.getCantidad());
			producto.setFecha_caducidad(productoView.getFecha_caducidad());
			producto.setP_publico( Float.parseFloat(productoView.getP_publico()));
			producto.setP_Mayoreo(Float.parseFloat(productoView.getP_Mayoreo()));
			producto.setP_Adquisicion(Float.parseFloat(productoView.getP_Adquisicion()));
			producto.setExistencia(Integer.parseInt(productoView.getExistencia()));
			producto.setCategoria(productoView.getCategoria());
			producto.setMarca(productoView.getMarca());
			
			respuesta.setRespuesta(producto);
			
		} catch (Exception e) {
			return new Respuesta("Error : "+e.getMessage(),false,null);
		}
		return respuesta;
	}
	
	public Respuesta validarProducto(int tipoOperacion, ProductoView productoView) {
		
		respuesta = new Respuesta("",true,productoView);
		Respuesta respuestaT = new Respuesta("",false,null); 
		ProductosDAO productosDAO = new ProductosDAO();
		
		try {	
			//Validar Codigo
			if(productoView.getCodigo().equals("") || productoView.getCodigo().isEmpty()) {
				return new Respuesta("Introduzca un Código de Producto Válido.",false,null);
			}
			
			respuestaT = productosDAO.obtenerProductoCodigo(productoView.getCodigo());
			
			//Tipo de Operacion
			switch (tipoOperacion) {
			case Herramientas.tipoOperacion.insertar:
				if(respuestaT.getRespuesta() != null )
					return new Respuesta("Ya Existe un Producto con Código :"+productoView.getCodigo(),false,null);
				break;
				
			case Herramientas.tipoOperacion.actualizar:
				if(respuestaT.getRespuesta() == null )
					return new Respuesta("No Existe un Producto con Código :"+productoView.getCodigo(),false,null);
				break;
			}
			
			if(productoView.getNombre().equals("") 	|| productoView.getNombre().isEmpty())
				return new Respuesta("Introduzca un Nombre para el producto.",false,null);
			
			if(productoView.getDescripcion().equals("") 	|| productoView.getDescripcion().isEmpty())
				return new Respuesta("Introduzca una Descripción para el producto.",false,null);
			
			if(productoView.getCantidad().equals("") 	|| productoView.getCantidad().isEmpty())
				return new Respuesta("Introduzca una cantidad descriptiva para el producto.",false,null);
			
			if(productoView.getDescripcion().equals("") 	|| productoView.getDescripcion().isEmpty())
				return new Respuesta("Introduzca una Descripción para el producto.",false,null);
			
			if( !Herramientas.validarFlotante(productoView.getP_publico()))
				return new Respuesta("Introducir una cantidad Correcto para Precio al Público",false,null);
			
			if( !Herramientas.validarFlotante(productoView.getP_Mayoreo()))
				return new Respuesta("Introducir una cantidad Correcto para Precio al Mayoreo",false,null);
			
			if( !Herramientas.validarFlotante(productoView.getP_Adquisicion()))
				return new Respuesta("Introducir una cantidad Correcto para Precio de Adquisición",false,null);
			
			if( !Herramientas.validarEntero( productoView.getExistencia()) )
				return new Respuesta("Introduzca una cantidad Correcta para la Existencia del Producto",false,null);
			
			if(productoView.getCategoria().equals("") || productoView.getCategoria().isEmpty())
				return new Respuesta("Introduzca una Categoría para el Producto",false,null);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Respuesta("Error : "+e.getMessage(),false,null);
		}
		
		return respuesta;
		
	}
	
}
