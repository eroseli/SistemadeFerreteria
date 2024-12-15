package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ModelsDAO.Categoria;
import DAO.ModelsDAO.MarcaDAO;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class MarcasDAO {
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	MarcaDAO marca =null;
	ArrayList<MarcaDAO> marcas = new ArrayList<MarcaDAO>();
	
	public Respuesta obtenerMarcas( ) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		String query = "select * from Marcas order by Nombre asc";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();			
			marcas.clear();
			
			while (resultados.next()) {
				marca = new MarcaDAO(
						resultados.getInt("id_Marca"),
                		resultados.getString("Nombre"),
                		resultados.getString("Descripcion"),
                		resultados.getString("Estatus"),
                		resultados.getDate("Fecha_creacion"),
                		resultados.getDate("Fecha_actualizacion")
                		);
				marcas.add(marca);
			}
			
			respuesta.setRespuesta(marcas);
			
		} catch (SQLException e) {
			// TODO: handle exception
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
	
	public Respuesta insertarMarca(MarcaDAO marca) {
		
		respuesta = new Respuesta("Marca Agregada Correctamente",true,null);
		String query = "INSERT INTO marcas (nombre, descripcion ) VALUES"
				+ "(?,?)";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
            
            stm.setString(1,marca.getNombre() );
            stm.setString(2,marca.getDescripcion() );
            stm.execute();
            
		} catch (SQLException e) {
			respuesta=  new Respuesta("Error al Registrar Marca. "+e.getMessage(),false,e.getErrorCode());
			System.out.println("Mensaje de ERROR"+e.getMessage());
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error :" + e.getMessage());
			}
		}		
		return respuesta;		
	}
	
	public Respuesta eliminarMarca(int idMarca) {
		respuesta = new Respuesta("Marca Eliminada Correctamente",true,null);
		String query = "DELETE FROM Marcas WHERE Id_Marca = "+idMarca+" ;";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);                       
            stm.execute();
            
		} catch (SQLException e) {
			respuesta=  new Respuesta("Error al Eliminar Marca. "+e.getMessage(),false,e.getErrorCode());
			System.out.println("Mensaje de ERROR"+e.getMessage());
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error :" + e.getMessage());
			}
		}		
		return respuesta;
	}

}
