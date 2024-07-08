package Controllers;

import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DAO.VentasDAO;
import DAO.ModelsDAO.REGISTROVENTADET;
import Models.Respuesta;
import Models.Venta;

public class ControllerVenta {

	Respuesta respuesta;
	
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
