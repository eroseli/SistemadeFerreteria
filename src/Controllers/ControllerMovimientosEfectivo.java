package Controllers;

import DAO.ModelsDAO.MovimientoEfectivo;
import HerramientasConexion.Herramientas;
import Models.Respuesta;
import Services.MercanciaService;
import Services.MovimientosEfectivoService;

public class ControllerMovimientosEfectivo {

	private Respuesta respuesta = null;
	private MovimientosEfectivoService movimientoEfectivoService = null;
	
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		movimientoEfectivoService = new MovimientosEfectivoService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.insertar:
			respuesta = movimientoEfectivoService.insertar( (MovimientoEfectivo) objeto);
			break;
		case Herramientas.tipoOperacion.actualizar:
			break;
		case Herramientas.tipoOperacion.eliminar:
			respuesta = movimientoEfectivoService.eliminar( (MovimientoEfectivo) objeto);
			break;
		case Herramientas.tipoOperacion.seleccionar:
			break;
		}
		return respuesta;	
	}
	
}

