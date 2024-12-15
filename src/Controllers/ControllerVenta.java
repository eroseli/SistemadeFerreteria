package Controllers;

import java.beans.EventSetDescriptor;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DAO.ProductosDAO;
import DAO.VentasDAO;
import DAO.ModelsDAO.REGISTROVENTADET;
import DAO.ModelsDAO.VentaTotalesDAO;
import HerramientasConexion.Herramientas;
import Models.ProductoVenta;
import Models.Respuesta;
import Models.Venta;

public class ControllerVenta {

	Respuesta respuesta;
	Respuesta respuestaC;
	
	public Respuesta guardarCarrito(String idCliente, List<ProductoVenta> productosVenta, String nombre) {
		
		respuesta = new Respuesta("",true,null);
		respuestaC = new Respuesta("",true,null);
		
		ProductosDAO dao = new ProductosDAO();
		
		respuesta = dao.obtenerNumeroCarritos();
		
		if(!respuesta.getValor()) {
			return respuesta;
		}
		
		if((Integer)respuesta.getRespuesta() == 12)
			return new Respuesta("Únicamente puedes registrar 12 Carritos como máximo.",false,null);
		
		
		respuesta = dao.NombreCarritoRepetido(nombre);
		
		if((Integer)respuesta.getRespuesta()>0 ) {
			return new Respuesta("Ya existe un Carrito con ese Nombre",false,null);
		}
		
		respuesta = dao.insertarCarrito(idCliente,nombre);
		
		if (!respuesta.getValor()) {
			return respuesta;
		}
		
		respuesta = dao.obtenerUltimoIdentificador();
		
		if (!respuesta.getValor()) {
			return respuesta;
		}
		
		for(ProductoVenta productoVenta: productosVenta) {
			
			respuestaC = dao.insertarDetalleCarrito(productoVenta, (int) respuesta.getRespuesta());	
			if (!respuestaC.getValor()) {
				return respuestaC;
			}
		}
		
		return new Respuesta("Carrito Guardado Correctamente",true,null);
		
	}
	
	public Respuesta obtenerTotalesDescripcionVentas(Date fechaInicio, Date fechaFinal) { // obtener descripcion de ventas totales
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaDao = new Respuesta("",true,null);
		VentasDAO ventasDAO = new VentasDAO();
		VentaTotalesDAO ventatotalDao = new VentaTotalesDAO();
		VentaTotalesDAO ventatotalDaoRespuesta = new VentaTotalesDAO();
		
		// obtener valores 
		respuestaDao = ventasDAO.obtenerValoresTotalesVentas(fechaInicio, fechaFinal);
		
		if (respuestaDao.getRespuesta() == null) {
			return respuesta;
		}
		
		ventatotalDaoRespuesta = (VentaTotalesDAO) respuestaDao.getRespuesta();
		
		// obtener producto estrella
		respuestaDao = ventasDAO.obtenerTotalesHistorialProductoEstrella(fechaInicio, fechaFinal);
		
		
		if(respuestaDao.getRespuesta() == null) {
			respuesta.setRespuesta(ventatotalDaoRespuesta);
			return respuesta;
		}
		
		ventatotalDao = (VentaTotalesDAO) respuestaDao.getRespuesta();	
		ventatotalDaoRespuesta.setProductoEstrella(ventatotalDao.getProductoEstrella()); // asigna unicamente el producto mas vendido
		
		// obtener descuentos aplicados
		respuestaDao = ventasDAO.obtenerTotalesHistorialDescuentosAplicados(fechaInicio, fechaFinal);
		if( respuestaDao.getRespuesta() == null) {
			respuesta.setRespuesta(ventatotalDaoRespuesta);
			return respuesta;
		}
		
		ventatotalDao = (VentaTotalesDAO) respuestaDao.getRespuesta();
		ventatotalDaoRespuesta.setDescuentoAplicado(ventatotalDao.getDescuentoAplicado());
		respuesta.setRespuesta(ventatotalDaoRespuesta);
		
		System.out.println(ventatotalDaoRespuesta.toString());
		
		return  respuesta;
	}
	
	public Respuesta eliminarCarrito(int idCarrito) {
		respuesta = new Respuesta("",true,null);
		respuestaC = new Respuesta("",true,null);
		ProductosDAO dao = new ProductosDAO();

		respuesta = dao.eliminarCarritoDet(idCarrito);	
		if(!respuesta.getValor())
			return respuesta;
		
		respuesta =  dao.eliminarCarrito( idCarrito);		
		return respuesta;
	}
	
	public Respuesta obtenerCarritos() {
		
		respuesta = new Respuesta("",true,null);
		ProductosDAO dao = new ProductosDAO();

		respuesta = dao.obtenerCarritos();
		
		return respuesta;
		
	}

	public Respuesta obtenerCarritoDetalleProductos(int idCarrito) {
		
		respuesta = new Respuesta("",true,null);
		ProductosDAO dao = new ProductosDAO();
		
		respuesta = dao.obtenerCarritoDetalleProductos(idCarrito);
		
		return respuesta;
		
	}
	
	public Respuesta procesarListaVenta(Venta venta) {
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaR = new Respuesta("",true,null);
		
		VentasDAO ventaDao = new VentasDAO();
		int ventas = 0;

		respuesta = ventaDao.REGISTROVENTA(venta.getRegistroventa());

		if (!respuesta.getValor()) {
			return new Respuesta ("Error al procesar registro de venta",false,null);
		}
		
		
		
		for (REGISTROVENTADET registroventadet : venta.getRegistroventadetList()) {
			
			//ventaDao.generarVenta();
			registroventadet.setParam_Id_Venta((int) respuesta.getRespuesta());
			//System.out.println("Antes de guardar en bd "+registroventadet.toString());
			respuestaR = ventaDao.REGISTROVENTADET(registroventadet);
			
			if (respuestaR.getValor() ){
				ventas++;
			}

		}
		
		if (ventas != venta.getRegistroventadetList().size()) {
			return new Respuesta ("Error al procesar registro de venta",false,null);
		}
				
		return respuesta;
	}
	
	public Respuesta obtenerventa(int tipoOperacion, Date fechaInicio, Date fechaFinal) {
		
		Respuesta respuesta = new Respuesta("",false,null);
		VentasDAO ventaDao = new VentasDAO();
		
		try {	
			respuesta = ventaDao.obtenerVentas(tipoOperacion, fechaInicio, fechaFinal);
		} catch (Exception e) {
			respuesta =  new Respuesta("Problemas al obtener las ventas",false,null);
		}		
		return respuesta;
	}
	
	public Respuesta obtenerHistorialProducto(int idHistorial) {
		
		Respuesta respuesta = new Respuesta("",false,null);
		VentasDAO ventasDao = new VentasDAO();
		
		try {
			respuesta = ventasDao.obtenerHistorialProducto(idHistorial);
		} catch (Exception e) {
			respuesta = new Respuesta("Problemas al obtener el historial del producto",false,null);
		}
		
		return respuesta;
		
	}
	
	public Respuesta obtenerventaFecha() {
		
		respuesta = new Respuesta("",false,null);
		VentasDAO ventaDao = new VentasDAO();
		
		try {
			
		} catch (Exception e) {
			respuesta = new Respuesta("Problemas al obtener las ventas",false,null);
		}
		return respuesta;
	}
	
	public Respuesta obtenerProductosVenta(int idHistorial) {
		
		Respuesta respuesta = new Respuesta("",false,null);
		VentasDAO ventaDao = new VentasDAO();
		
		try {
			respuesta = ventaDao.obtenerProductosVenta(idHistorial);
		} catch (Exception e) {
			respuesta =  new Respuesta("Problemas al obtener las ventas",false,null);
		}
		return respuesta;
	}
	
	public Respuesta procesarVenta(Venta venta) {
		
		
		
		return respuesta;
	}
	
	
	
	public static void main(String[]args) {
		
		REGISTROVENTADET prod1 = new REGISTROVENTADET(1,2,0,100,"No",0,"1",0,"");
		REGISTROVENTADET prod2 = new REGISTROVENTADET(2,2,0,80,"No",0,"1",0,"");
		REGISTROVENTADET prod3 = new REGISTROVENTADET(3,2,0,50,"No",0,"1",0,"");		
		DAO.ModelsDAO.REGISTROVENTA registroventa = new DAO.ModelsDAO.REGISTROVENTA(1, "1", 3, 230, 0, 180, 0, 180, 180, 0, null);
		ControllerVenta controllerVenta = new ControllerVenta();
		
		ArrayList<REGISTROVENTADET> r = new ArrayList<REGISTROVENTADET>();
		r.add(prod1);
		r.add(prod2);
		r.add(prod3);

		
		Venta venta = new Venta();
		
		venta.setRegistroventa(registroventa);
		
		venta.setRegistroventadetList(r);
		
		Respuesta respuesta = controllerVenta.procesarListaVenta(venta);
		
		System.out.println(respuesta.getMensaje());
		
		
	}
	
}
