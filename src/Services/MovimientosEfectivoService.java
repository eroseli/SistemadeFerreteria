package Services;

import java.sql.Date;

import DAO.MovimientosEfectivoDAO;
import DAO.ModelsDAO.MovimientoEfectivo;
import Models.Respuesta;

public class MovimientosEfectivoService {
	
	Respuesta respuesta = new Respuesta("",true,null);
	
	public Respuesta seleccionar() {
		
		MovimientosEfectivoDAO movimientosEfectivoDAO = new MovimientosEfectivoDAO();
		
		return movimientosEfectivoDAO.obtenerMovimientosEfectivo(null, null);
		
	}
	
	public Respuesta obtenerMovimientosEfectivo(Date FInicio, Date FFinal) {
		
		MovimientosEfectivoDAO movimientosEfectivoDAO = new MovimientosEfectivoDAO();	
		return movimientosEfectivoDAO.obtenerMovimientosEfectivo(FInicio, FFinal);
	}
	
	public Respuesta insertar(MovimientoEfectivo movimientoEfectivo) {
		MovimientosEfectivoDAO movimientosEfectivoDAO = new MovimientosEfectivoDAO();
		return movimientosEfectivoDAO.insertarMovimientoEfectivo(movimientoEfectivo);
	}
	
	public Respuesta eliminar(MovimientoEfectivo movimientoEfectivo) {
		MovimientosEfectivoDAO movimientosEfectivoDAO = new MovimientosEfectivoDAO();
		return movimientosEfectivoDAO.eliminarMovimientoEfectivo(movimientoEfectivo);
	}

}
