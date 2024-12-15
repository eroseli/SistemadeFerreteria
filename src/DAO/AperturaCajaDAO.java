package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAO.ModelsDAO.AperturaCaja;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class AperturaCajaDAO {
	
	private PreparedStatement stm = null;
    private ResultSet resultados = null;
    private Respuesta respuesta;
    private List<AperturaCaja> aperturasCaja = null;
    private String query = "";
    AperturaCaja aperturaCaja = new AperturaCaja();
    public Respuesta obtenerAperturasCaja() {
        respuesta = new Respuesta("", true, null);
        query = "SELECT * FROM AperturasCaja WHERE Estado = 'CERRADO'";  // Solo obtener aperturas cerradas
        aperturasCaja = new ArrayList<AperturaCaja>();
        
        try {
            ConexionGlobal.establecerConexio();
            stm = ConexionGlobal.connection.prepareStatement(query);
            resultados = stm.executeQuery();
            
            while (resultados.next()) {
            	aperturaCaja = new AperturaCaja(
                		resultados.getInt("Id_Apertura"),
                        resultados.getTimestamp("Fecha_apertura"),
                        resultados.getTimestamp("Fecha_cierre"),
                        resultados.getBigDecimal("Monto_apertura"),
                        resultados.getBigDecimal("Monto_cierre"),
                        resultados.getString("Observaciones_Apertura"),
                        resultados.getString("Observaciones_Cierre"),
                        resultados.getString("Estado"),
                        resultados.getInt("Id_Usuario")
                );
                
                aperturasCaja.add(aperturaCaja);
            }
            
            respuesta.setRespuesta(aperturasCaja);
            
        } catch (SQLException e) {
            respuesta = new Respuesta("Error al intentar obtener las aperturas de caja: " + e.getMessage(), false, null);
            e.printStackTrace();
        } finally {
            try {
                ConexionGlobal.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return respuesta;
    }
    
    public Respuesta obtenerCajasFecha(Date fechaInicial, Date fechaFinal) {
    	respuesta = new Respuesta("", true, null);
        query = "select * from AperturasCaja where  Date(Fecha_apertura) >= '"+fechaInicial+"' and Date(Fecha_apertura) <= '"+fechaFinal+"';";  
        aperturasCaja = new ArrayList<AperturaCaja>();
        System.out.println(query);
        try {
            ConexionGlobal.establecerConexio();
            stm = ConexionGlobal.connection.prepareStatement(query);
            resultados = stm.executeQuery();
            
            while (resultados.next()) {
            	aperturaCaja = new AperturaCaja(
                		resultados.getInt("Id_Apertura"),
                        resultados.getTimestamp("Fecha_apertura"),
                        resultados.getTimestamp("Fecha_cierre"),
                        resultados.getBigDecimal("Monto_apertura"),
                        resultados.getBigDecimal("Monto_cierre"),
                        resultados.getString("Observaciones_Apertura"),
                        resultados.getString("Observaciones_Cierre"),
                        resultados.getString("Estado"),
                        resultados.getInt("Id_Usuario")
                );
                
                aperturasCaja.add(aperturaCaja);
            }
            
            respuesta.setRespuesta(aperturasCaja);
            
        } catch (SQLException e) {
            respuesta = new Respuesta("Error al intentar obtener las aperturas de caja: " + e.getMessage(), false, null);
            e.printStackTrace();
        } finally {
            try {
                ConexionGlobal.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return respuesta;
    }
    
    
    public Respuesta verificarCajaAbierta() {
    	respuesta  = new Respuesta("Cajas Cerradas",true,null);
        AperturaCaja  aperturaCaja = new AperturaCaja();
    	query =  "SELECT * FROM AperturasCaja WHERE Estado = 'ABIERTA' LIMIT 1";
        
        try {
            ConexionGlobal.establecerConexio();
            stm = ConexionGlobal.connection.prepareStatement(query);
            resultados = stm.executeQuery();
            
            if (resultados.next()) {
                aperturaCaja = new AperturaCaja(
                		resultados.getInt("Id_Apertura"),
                        resultados.getTimestamp("Fecha_apertura"),
                        resultados.getTimestamp("Fecha_cierre"),
                        resultados.getBigDecimal("Monto_apertura"),
                        resultados.getBigDecimal("Monto_cierre"),
                        resultados.getString("Observaciones_Apertura"),
                        resultados.getString("Observaciones_Cierre"),
                        resultados.getString("Estado"),
                        resultados.getInt("Id_Usuario")
                );
                respuesta.setRespuesta(aperturaCaja);
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConexionGlobal.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return respuesta;
    }
    
    public Respuesta obtenerAperturaCajaPorId(int idApertura) {
        respuesta = new Respuesta("", true, null);
        query = "SELECT * FROM AperturasCaja WHERE Id_Apertura = ?";
        AperturaCaja  aperturaCaja = new AperturaCaja();
        
        try {
            ConexionGlobal.establecerConexio();
            
            stm = ConexionGlobal.connection.prepareStatement(query);
            
            stm.setInt(1, idApertura);
            resultados = stm.executeQuery();
            
            if (resultados.next()) {
            	aperturaCaja = new AperturaCaja(
                		resultados.getInt("Id_Apertura"),
                        resultados.getTimestamp("Fecha_apertura"),
                        resultados.getTimestamp("Fecha_cierre"),
                        resultados.getBigDecimal("Monto_apertura"),
                        resultados.getBigDecimal("Monto_cierre"),
                        resultados.getString("Observaciones_Apertura"),
                        resultados.getString("Observaciones_Cierre"),
                        resultados.getString("Estado"),
                        resultados.getInt("Id_Usuario")
                );
            }
            
            respuesta.setRespuesta(aperturaCaja);
            
        } catch (SQLException e) {
            respuesta = new Respuesta("Error al obtener la apertura de caja: " + e.getMessage(), false, null);
            e.printStackTrace();
        } finally {
            try {
                ConexionGlobal.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return respuesta;
    }
    
 // Actualizar la caja (Cierre de caja)
    public Respuesta cerrarAperturaCaja(AperturaCaja aperturaCaja) {
        respuesta = new Respuesta("Cierre de Caja Procesasdo Correctamente.", true, null);
        query = "UPDATE AperturasCaja SET Monto_cierre = ?, Fecha_cierre = ?, Estado = 'CERRADA', Observaciones_Cierre = ? WHERE  Id_Apertura = ?";
        
        try {
            ConexionGlobal.establecerConexio();
            stm = ConexionGlobal.connection.prepareStatement(query);
            stm.setBigDecimal(1, aperturaCaja.getMontoCierre());
            stm.setTimestamp(2,  new Timestamp(System.currentTimeMillis()));
            stm.setString(3, aperturaCaja.getObservaciones());
            stm.setInt(4, aperturaCaja.getIdApertura());
            stm.execute();
        } catch (SQLException e) {
            respuesta = new Respuesta("Error al cerrar la apertura de caja: " + e.getMessage(), false, null);
            e.printStackTrace();
        } finally {
            try {
                ConexionGlobal.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return respuesta;
    }
    
    
    public Respuesta insertarAperturaCaja(AperturaCaja apertura) {
        
    	respuesta = new Respuesta("Apertura de Caja Registrada Exitosamente.", true, null);
        query = "INSERT INTO AperturasCaja (Monto_apertura,Monto_cierre, Id_Usuario, Observaciones_Apertura) VALUES (?,?,?,?)";
        
        try {
            ConexionGlobal.establecerConexio();
            stm = ConexionGlobal.connection.prepareStatement(query);
            stm.setBigDecimal(1, apertura.getMontoApertura());
            stm.setBigDecimal(2, apertura.getMontoCierre());
            stm.setInt(3, apertura.getIdUsuario());
            stm.setString(4, apertura.getObservaciones());      
            stm.execute();
        } catch (SQLException e) {
            respuesta = new Respuesta("Error al insertar la apertura de caja: " + e.getMessage(), false, null);
            e.printStackTrace();
        } finally {
            try {
                ConexionGlobal.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return respuesta;
    }
    

}
