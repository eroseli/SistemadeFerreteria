package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ModelsDAO.Categoria;
import DAO.ModelsDAO.Producto;
import HerramientasConexion.ConexionGlobal;
import Models.Respuesta;

public class CategoriasDAO {
	
	PreparedStatement stm = null;
	ResultSet resultados = null;
	Respuesta respuesta;
	Categoria categoria =null;
	ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	
	public Respuesta obtenerCategorias( ) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		String query = "select * from categorias order by nombre asc";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();			
			categorias.clear();
			
			while (resultados.next()) {
				categoria = new Categoria(
						resultados.getInt("id_categoria"),
                		resultados.getString("nombre"),
                		resultados.getString("descripcion"),
                		resultados.getString("tipo_vehiculo")
                		);
				categorias.add(categoria);
			}
			
			respuesta.setRespuesta(categorias);
			
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
	
	public Respuesta insertarCategoria(Categoria categoria) {
		
		respuesta = new Respuesta("Categoría Agregada Correctamente",true,null);
		String query = "INSERT INTO categorias (nombre, descripcion, tipo_vehiculo) VALUES"
				+ "(?,?,?)";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
            
            stm.setString(1,categoria.getNombre() );
            stm.setString(2,categoria.getDescripcion() );
            stm.setString(3,categoria.getTipo_vehiculo() );
            stm.execute();
            
		} catch (SQLException e) {
			respuesta=  new Respuesta("Error al Registrar Categoría. "+e.getMessage()
				+" NO. "+e.getErrorCode(),false,e.getErrorCode());
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

	public Respuesta eliminarCategoria(int idCategoria) {
		
		respuesta = new Respuesta("Categoría Eliminada Correctamente",true,null);
		String query = "DELETE FROM categorias WHERE id_categoria = "+idCategoria+" ;";
		
		try {
			
			ConexionGlobal.establecerConexio();
			stm = (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);		
			stm.execute();		
			
		} catch (SQLException e) {
			respuesta=  new Respuesta("Error al Eliminar Categoría. "+e.getMessage()
			+" NO. "+e.getErrorCode(),false,e.getErrorCode());
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
