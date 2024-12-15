package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.ModelsDAO.MarcaDAO;
import HerramientasConexion.ConexionGlobal;
import Models.Empresa;
import Models.Respuesta;

public class EmpresaDAO {

	PreparedStatement stm = null;
	ResultSet resultados = null;
	Empresa empresa = null;
	
	public Respuesta actualizarEmpresa(Empresa empresa) {
		
		Respuesta respuesta = new Respuesta("Datos de Empresa Actulizados Correctamente.",true,null);
		String query = "UPDATE Empresa SET Nombre = ?, Direccion = ?, NombrePropietario = ?,"
				+ "    ApaternoPropietario = ?, AmaternoPropietario = ?, Telefono = ?, Telefono2 = ?, Correo = ? "
				+ "WHERE Id_Empresa = ? ;";
		
		try {
			System.out.println(query);
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           

            System.out.println(1);
            
            System.out.println(empresa.getIdEmpresa());
            System.out.println(empresa.getApaternoPropietario());
            
			stm.setString(1, empresa.getNombre());
			stm.setString(2, empresa.getDireccion());
			stm.setString(3, empresa.getNombrePropietario());
			stm.setString(4, empresa.getApaternoPropietario());
			stm.setString(5, empresa.getAmaternoPropietario());
			stm.setString(6, empresa.getTelefono());
			stm.setString(7, empresa.getTelefono2());
			stm.setString(8, empresa.getCorreo());
			stm.setInt(9,empresa.getIdEmpresa());
			System.out.println(2);
			System.out.println(stm.getMetaData());
			stm.execute();
			
		} catch (SQLException e) {
			respuesta = new Respuesta("Error al actualizar la Empresa "+e.getMessage(),false,null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(3);
		return respuesta;
	}
	
	public Respuesta obtenerEmpresa() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		String query = "select * from Empresa";
		
		try {
			
			ConexionGlobal.establecerConexio();
            stm =  (PreparedStatement) ConexionGlobal.connection.prepareStatement(query);           
			resultados = stm.executeQuery();			
			
			while (resultados.next()) {
				empresa = new Empresa();
				empresa.setIdEmpresa(resultados.getInt("Id_Empresa"));
				empresa.setNombre(resultados.getString("Nombre"));
				empresa.setDireccion(resultados.getString("Direccion"));
				empresa.setNombrePropietario(resultados.getString("NombrePropietario"));
				empresa.setApaternoPropietario(resultados.getString("ApaternoPropietario"));
				empresa.setAmaternoPropietario(resultados.getString("AmaternoPropietario"));
				empresa.setTelefono(resultados.getString("Telefono"));
				empresa.setTelefono2(resultados.getString("Telefono2"));
				empresa.setCorreo(resultados.getString("Correo"));
				respuesta.setRespuesta(empresa);
			}
						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Respuesta("Error: "+e.getMessage(),false,null);
		}
		finally {
			try {
				ConexionGlobal.cerrarConexion();
			} catch (SQLException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}		
		
		return respuesta;
		
	}
	
}
