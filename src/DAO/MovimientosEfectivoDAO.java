package DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DAO.ModelsDAO.MovimientoEfectivo;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class MovimientosEfectivoDAO {

	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta = new Respuesta("",true,null);
	ArrayList<MovimientoEfectivo> movimientos = null;
	MovimientoEfectivo movimientoEfectivo = new MovimientoEfectivo();
	String query = "";
	
public Respuesta obtenerMovimientosEfectivo(Date fechaInicio ,Date fechaFinal ) {
		
		respuesta = new Respuesta("",true,null);
		query  = "select ME.*,CONCAT(U.nombre,\" \",U.apaterno,\" \",U.amaterno) usuario "
				+ "from MovimientosEfectivo ME "
				+ "LEFT JOIN Usuarios U ON ME.IdUsuario = U.Id_Usuario "
				+ "where ME.Estatus = 'ACTIVO' AND  DATE(ME.FechaRegistro) BETWEEN '"+fechaInicio+"' AND '"+fechaFinal+"';";
		
		movimientos = movimientos = new ArrayList<MovimientoEfectivo>();
		
		try {
			System.out.println(query);
			movimientos.clear();
			ConexionGlobal.establecerConexio();
			PreparedStatement stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);
			resultados = stm.executeQuery();
			
			while (resultados.next()) {
				
				movimientoEfectivo = new MovimientoEfectivo();
				movimientoEfectivo.setIdMovimientoEfectivo(resultados.getInt("Id_MovimientoEfectivo"));
				movimientoEfectivo.setTipoTransaccion(resultados.getString("Tipo_transaccion"));
				movimientoEfectivo.setMonto(resultados.getBigDecimal("Monto"));
				movimientoEfectivo.setConcepto(resultados.getString("Concepto"));
				movimientoEfectivo.setFechaRegistro(resultados.getTimestamp("FechaRegistro"));
				movimientoEfectivo.setIdUsuario(resultados.getString("usuario"));
				movimientoEfectivo.setMetodoPago(resultados.getString("Metodo_pago"));
				movimientoEfectivo.setObservaciones(resultados.getString("Observaciones"));
				movimientoEfectivo.setEstadoMovimiento(resultados.getString("EstadoMovimiento"));
				movimientoEfectivo.setEstatus(resultados.getString("Estatus"));

				
				movimientos.add(movimientoEfectivo);
				
			}
			
			respuesta.setRespuesta(movimientos);
			
		}  catch (SQLException e) {
			respuesta = new Respuesta("Error al intentar obtener los Clientes"+e.getMessage(), false, null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error : "+e.getMessage());
				respuesta = new Respuesta("Error : "+e.getMessage(),false,null);
			}
		}
		
		return respuesta;
		
	}
	
	public Respuesta insertarMovimientoEfectivo(MovimientoEfectivo movimientoEfectivo) {
		
		respuesta = new Respuesta("Movimiento de Efectivo insertado Correctamente",true,null);
		query = "insert into MovimientosEfectivo(Tipo_transaccion, Monto, Concepto, IdUsuario, Metodo_Pago, Observaciones, EstadoMovimiento, Estatus)"
				+"values(?,?,?,?,?,?,?,?)";
		
		try {
			
			ConexionGlobal.establecerConexio();	
            stm =  ConexionGlobal.connection.prepareStatement(query);           
			
            stm.setString(1,movimientoEfectivo.getTipoTransaccion());
            stm.setBigDecimal(2,movimientoEfectivo.getMonto());
            stm.setString(3,movimientoEfectivo.getConcepto());
            stm.setString(4,movimientoEfectivo.getIdUsuario());
            stm.setString(5,movimientoEfectivo.getMetodoPago());
            stm.setString(6,movimientoEfectivo.getObservaciones());
            stm.setString(7,movimientoEfectivo.getEstadoMovimiento());
            stm.setString(8,movimientoEfectivo.getEstatus());
            stm.execute();

			
		}catch (Exception e) {
			respuesta = new Respuesta("Error al Registrar el Movimiento de Efectivo. "+e.getMessage(), false, null);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return respuesta;
		
	}
	
	public Respuesta eliminarMovimientoEfectivo(MovimientoEfectivo movimientoEfectivo) {
		respuesta = new Respuesta("Movimiento de Efectivo Eliminado Correctamente",true,null);
		
		String query = "update MovimientosEfectivo set Estatus = 'INACTIVO' where Id_MovimientoEfectivo = ? ;";
		System.out.println(query + movimientoEfectivo.getIdMovimientoEfectivo());
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			
			stm.setInt(1,movimientoEfectivo.getIdMovimientoEfectivo() );
			stm.execute();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al Eliminar el  Movimiento de Efectivo con Folio "+movimientoEfectivo.getIdMovimientoEfectivo(), false, null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return respuesta;
	}

	
}
