package Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import DAO.ModelsDAO.MercanciaDAO;
import DAO.ModelsDAO.REGISTROMERCANCIAPRO;
import Models.MercanciaDetView;
import Models.Respuesta;

public class MercanciaService {
	
	Respuesta respuesta = null;
	DAO.MercanciaDAO mercanciaDAO = new DAO.MercanciaDAO();
	
	public interface PROCESO{	
		public static final int CABECERA =1;
		public static final int DETALLE = 2;
		public static final int ELIMINAR = 3;
	} 
	
	
	public Respuesta insertar(Object mercancia) {
		
		DAO.MercanciaDAO dao = new DAO.MercanciaDAO();
		respuesta = new Respuesta("",true,null);
		Map<String,Object > map = (Map<String,Object >) mercancia;
		REGISTROMERCANCIAPRO registromercanciapro = new REGISTROMERCANCIAPRO();
		ArrayList<REGISTROMERCANCIAPRO> listRegistroMercanciaPro = new ArrayList<REGISTROMERCANCIAPRO>();
		
		MercanciaDAO mercanciaDAO = (MercanciaDAO) map.get("mercanciaDAO");
		ArrayList<MercanciaDetView> mercanciasdetView = (ArrayList<MercanciaDetView>) map.get("mercanciasDet");
		System.out.println("Taqamanio arreglo "+mercanciasdetView.size());
		if (mercanciaDAO.getId_HistorialCompra() !=0) {
			//proceso grabar eliminacion
			respuesta = traducir(PROCESO.ELIMINAR, mercanciaDAO);
			respuesta =  dao.REGISTROMERCANCIAPRO(null);
			
			if (!respuesta.getValor()) 
				return respuesta;
		}
		
		
		//proceso grabar encabezado
		respuesta = traducir(PROCESO.CABECERA, mercanciaDAO);		
		//proceso grabar encabezado
		respuesta = dao.REGISTROMERCANCIAPRO((REGISTROMERCANCIAPRO) respuesta.getRespuesta());
		
		if (!respuesta.getValor())
			return respuesta;
		
		mercanciaDAO.setId_HistorialCompra( (Integer) respuesta.getRespuesta());
		
		map.remove("mercanciaDAO");
		map.put("mercanciaDAO",mercanciaDAO);
		
		//proceso grabar detalle
		respuesta = traducir(PROCESO.DETALLE, map);
		
		listRegistroMercanciaPro =  (ArrayList<REGISTROMERCANCIAPRO>) respuesta.getRespuesta();
		System.out.println("Tamanio "+listRegistroMercanciaPro.size());
		for (REGISTROMERCANCIAPRO rmp : listRegistroMercanciaPro) {
			
			respuesta = dao.REGISTROMERCANCIAPRO(rmp);
			
			if (!respuesta.getValor()) {
				return respuesta;
			}
		}
		
		if (!respuesta.getValor()) 
			return respuesta;	
		
		return respuesta;
		
	}
	
	public Respuesta traducir(int tipoOperacion, Object objeto) {
		Respuesta respuesta = new Respuesta("",true,null);
		REGISTROMERCANCIAPRO registromercanciapro = new REGISTROMERCANCIAPRO();
		ArrayList<REGISTROMERCANCIAPRO> listRegistroMercanciaPro =  new ArrayList<REGISTROMERCANCIAPRO>();
		MercanciaDAO mercanciaDAO  = null;
		
		switch (tipoOperacion) {
		case PROCESO.CABECERA:
			
			mercanciaDAO = (MercanciaDAO) objeto;
			
			registromercanciapro.setTipoOperacion(PROCESO.CABECERA);
			registromercanciapro.setIdHistorialCompra(mercanciaDAO.getId_HistorialCompra());
			registromercanciapro.setIdProveedor(mercanciaDAO.getId_Proveedor());
			registromercanciapro.setTotalProductos(mercanciaDAO.getTotalProductos());
			registromercanciapro.setTotalCompra(mercanciaDAO.getTotalCompra());
			registromercanciapro.setDescripcion(mercanciaDAO.getDescripcion());
			registromercanciapro.setEstadoPago(mercanciaDAO.getEstadoPago());
			registromercanciapro.setTipo(mercanciaDAO.getTipoOperacion());
			
			registromercanciapro.setIdProducto(0);
			registromercanciapro.setCantidad(0);
			registromercanciapro.setPrecioUnitario(0);
			registromercanciapro.setPrecioTotal(0);
				
			respuesta.setRespuesta(registromercanciapro);
			
			break;
		case PROCESO.DETALLE:
			
			Map<String,Object > map = (Map<String,Object >) objeto;			
			MercanciaDAO mercanciaDao = (MercanciaDAO) map.get("mercanciaDAO");
			ArrayList<MercanciaDetView> mercanciasdetView = (ArrayList<MercanciaDetView>) map.get("mercanciasDet");
			System.out.println("Tamanio mercancia traduccion "+mercanciasdetView.size());			
			mercanciasdetView.forEach(m -> listRegistroMercanciaPro.add(
					new REGISTROMERCANCIAPRO(
							PROCESO.DETALLE,
							mercanciaDao.getId_HistorialCompra(),
							mercanciaDao.getId_Proveedor(),
							mercanciaDao.getTotalProductos(),
							mercanciaDao.getTotalCompra(),
							m.getDescripcion(),
							mercanciaDao.getEstadoPago(),
							mercanciaDao.getTipoOperacion(),//
							m.getId_Producto(),
							m.getCantidad(),
							m.getPrecioUnitario(),
							m.getPrecioTotal(),
							0,
							""
							)
					));
				respuesta.setRespuesta(listRegistroMercanciaPro);
			break;
		case PROCESO.ELIMINAR:
			mercanciaDAO = (MercanciaDAO) objeto;
			registromercanciapro.setIdHistorialCompra(mercanciaDAO.getId_HistorialCompra());
			respuesta.setRespuesta(registromercanciapro);
			break;
		default:
			break;
		}
		
		return respuesta;
		
	}
	
	public Respuesta actualizar(MercanciaDAO mercancia) {return null;}
	
	
	public Respuesta obtenerVentaFechas(Date FInicio, Date FFinal) {
	
		respuesta = new Respuesta("",true,null);
		
		respuesta  = mercanciaDAO.obtenerCompras(FInicio, FFinal);
		
		return respuesta;
	}
	
	public Respuesta obtenerDetalleCompra(int idMercancia) {
		
		respuesta = new Respuesta("",true,null);
		
		respuesta = mercanciaDAO.obtenerDetalleCompra(idMercancia);
		
		return respuesta;
		
	}
	
	public Respuesta grabar(){
		
		
		
		
		respuesta = new Respuesta("",true,null);
		
		return respuesta;
		
	}
	
	public Respuesta eliminar(int idEliminar) {return null;}
	
	

}
