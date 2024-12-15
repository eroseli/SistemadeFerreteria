package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ModelsDAO.MarcaDAO;
import DAO.ModelsDAO.PuestoDAO;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class PuestosDAO {

	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	PuestoDAO puesto =null;
	ArrayList<PuestoDAO> puestos = new ArrayList<PuestoDAO>();
	
	public Respuesta obtenerMarcas( ) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		String query = "select * from Puestos where Estatus = 'ACTIVO' order by Nombre asc";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();			
			puestos.clear();
			
			while (resultados.next()) {
				puesto = new PuestoDAO(
						resultados.getInt("Id_puesto"),
                		resultados.getString("Nombre"),
                		resultados.getString("Descripcion"),
                		resultados.getString("Estatus"),
                		resultados.getInt("Nivel_jerarquico")
                		);
				puestos.add(puesto);
			}
			
			respuesta.setRespuesta(puestos);
			
		} catch (SQLException e) {
			return new Respuesta("Error: "+e.getMessage(),false,null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}		
		return  respuesta;
	}
	
}
