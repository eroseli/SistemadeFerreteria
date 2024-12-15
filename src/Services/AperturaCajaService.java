package Services;

import java.sql.Date;

import DAO.AperturaCajaDAO;
import DAO.ModelsDAO.AperturaCaja;
import Models.Respuesta;

public class AperturaCajaService {
	
	
	Respuesta respuesta = new Respuesta("",true,null);
	AperturaCajaDAO aperturaCajaDAO = null;
	
	
	public Respuesta insertar(AperturaCaja apertursCaja) {
		aperturaCajaDAO = new AperturaCajaDAO();
		return aperturaCajaDAO.insertarAperturaCaja(apertursCaja);
	}
	
	public Respuesta seleccionarCajas() {
		aperturaCajaDAO = new AperturaCajaDAO();
		return aperturaCajaDAO.obtenerAperturasCaja();
	}
	
	public Respuesta seleccionCajasFecha(Date fechaInicial, Date fechaFinal) {
		aperturaCajaDAO = new AperturaCajaDAO();
		return aperturaCajaDAO.obtenerCajasFecha(fechaInicial, fechaFinal);
	}
	
	public Respuesta seleccionarCajaId(int idCaja) {
		aperturaCajaDAO = new AperturaCajaDAO();
		return aperturaCajaDAO.obtenerAperturaCajaPorId(idCaja);
	}
	
	public Respuesta cerrarCaja(AperturaCaja aperturaCaja) {
		aperturaCajaDAO = new AperturaCajaDAO();
		return aperturaCajaDAO.cerrarAperturaCaja(aperturaCaja);
	}
	
	public Respuesta verificarCajaAbierta() {
		aperturaCajaDAO = new AperturaCajaDAO();
		return aperturaCajaDAO.verificarCajaAbierta();
		
	}
	
}
