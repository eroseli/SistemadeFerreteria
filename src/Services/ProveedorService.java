package Services;

import java.awt.image.RescaleOp;
import java.util.ArrayList;

import DAO.ClientesDAO;
import DAO.ProveedoresDAO;
import DAO.ModelsDAO.Cliente;
import DAO.ModelsDAO.Proveedor;
import HerramientasConexion.Herramientas;
import Models.ClienteView;
import Models.ProveedorView;
import Models.Respuesta;

public class ProveedorService {

	Respuesta respuesta;
	ProveedoresDAO  proveedoresDAO;
	
	public Respuesta insertar(ProveedorView proveedorView) {
		respuesta = new Respuesta("",true,null);
		
		respuesta = validarProveedor(Herramientas.tipoOperacion.insertar, proveedorView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = TraducirProveedor(proveedorView, Herramientas.tipoOperacion.insertar);
		if(!respuesta.getValor())
			return respuesta;
		
		respuesta = realizarInsercion((Proveedor) respuesta.getRespuesta());
		return respuesta;
	}
	
	public Respuesta realizarInsercion(Proveedor proveedor) {
		respuesta = new Respuesta("",true,null);
		proveedoresDAO = new ProveedoresDAO();
		respuesta = proveedoresDAO.insertarProveedor(proveedor);
		return respuesta;
	}
	
	public Respuesta actualizar(ProveedorView proveedorView) {
		respuesta = new Respuesta("",true, null);
		
		respuesta = validarProveedor(Herramientas.tipoOperacion.actualizar, proveedorView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = TraducirProveedor(proveedorView, Herramientas.tipoOperacion.actualizar);
		if(!respuesta.getValor())
			return respuesta;
		
		respuesta = realizarActualizacion((Proveedor) respuesta.getRespuesta());
		return respuesta;
	}
	
	public Respuesta realizarActualizacion(Proveedor proveedor) {
		respuesta = new Respuesta("",true, null);
		proveedoresDAO = new ProveedoresDAO();
		respuesta = proveedoresDAO.actualizarProveedor(proveedor);
		return respuesta;
	}
	
	public Respuesta eliminar(String  idProveedor) {
		respuesta = new Respuesta("",true, null);
		Respuesta respuestaT = new Respuesta("",false,null);
		proveedoresDAO = new ProveedoresDAO();
		respuesta = proveedoresDAO.eliminarProveedor(idProveedor);
		
		respuestaT = proveedoresDAO.obtenerProveedorIdproveedor(idProveedor);
		if ( !(respuestaT.getRespuesta() == null) )
			return new Respuesta("Problemas al intentar Eliminar al Cliente "+idProveedor,false,null);
		return respuesta;
	}
	
	public Respuesta seleccionar(String nombre) {
		respuesta = new Respuesta("",true, null);
		ProveedoresDAO dao =new ProveedoresDAO();
		
		if ( nombre == null || nombre.isEmpty() || nombre.equals("")) {
			return dao.obtenerProveedores();
		}
		return dao.obtenerProveedorDescripcion(nombre);		
	}
	
	public Respuesta seleccionarId(String idProveedor) {
		ProveedoresDAO dao =new ProveedoresDAO();
		return dao.obtenerProveedorIdproveedor(idProveedor);	
	}
	
	public Respuesta TraducirProveedor(ProveedorView proveedorView, int tipoOperacion) {
		
		respuesta = new Respuesta("",true,null);
		Proveedor proveedor = new Proveedor();
		
		try {
			proveedor.setNombre(proveedorView.getNombre());
			proveedor.setApaterno(proveedorView.getApaterno());
			proveedor.setAmaterno(proveedorView.getAmaterno());
			proveedor.setTelefono(proveedorView.getNumTelefono());
			proveedor.setCorreo(proveedorView.getCorreo());
			proveedor.setEmpresa(proveedorView.getEmpresa());
			proveedor.setDireccion(proveedorView.getDireccion());
			proveedor.setFechaRegistro(proveedorView.getFechaRegistro());
			proveedor.setTipoProducto(proveedorView.getTipoProducto());
			proveedor.setNotasAdicionales(proveedorView.getNotasAdicionales());
			
			if (tipoOperacion == Herramientas.tipoOperacion.insertar) {
				respuesta  = generalIdentificador(proveedor);
				if (!respuesta.getValor())
					return respuesta;
				proveedor.setId_Proveedor( (String) respuesta.getRespuesta());
			}	
			else
				proveedor.setId_Proveedor(proveedorView.getId_Proveedor());
			
			respuesta.setRespuesta(proveedor);
			
		} catch (Exception e) {
			return new Respuesta("Error al intentar Traducir valores : "+e.getMessage(),false,null);
		}
		
		return respuesta;
	}
	
	public Respuesta generalIdentificador(Proveedor proveedor) {
		
	proveedoresDAO = new ProveedoresDAO();
	respuesta = new Respuesta("",true,null);
	String idProveedor = "";
		
	try {
		
		respuesta = proveedoresDAO.obtenerCantidadProveedores();
		if(!respuesta.getValor())
			return new Respuesta ("Error al intentar Generar Identificador del Cliente"+respuesta.getMensaje(),false,null);
		
		idProveedor = idProveedor + proveedor.getApaterno().charAt(0);
		idProveedor = idProveedor + proveedor.getAmaterno().charAt(0);
		idProveedor = idProveedor + proveedor.getNombre().charAt(0);
		idProveedor = idProveedor + respuesta.getRespuesta().toString();
		respuesta.setRespuesta(idProveedor);
	} catch (Exception e) {
		return new Respuesta ("Error al intentar Generar Identificador del Proveedor "+e.getMessage(),false,null);
	}
	
	return respuesta;
	}
	
	public Respuesta validarProveedor(int tipoOperacion, ProveedorView proveedorView) {
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaT = new Respuesta("",false,null);
		proveedoresDAO = new ProveedoresDAO();
		try {
			
			if(proveedorView.getNumTelefono().equals("") || proveedorView.getNumTelefono().isEmpty())
				return new Respuesta("Introduzca un Teléfono Celular Válido",false,null);
						
			//tipo de Operacion
			switch (tipoOperacion) {
			case Herramientas.tipoOperacion.insertar:

				respuestaT =  proveedoresDAO.obtenerProveedorTelefono(  proveedorView.getNumTelefono());
				
				if(!respuestaT.getValor())
					return respuestaT;
				
				if( ((ArrayList<Proveedor>)respuestaT.getRespuesta()).size()> 0)
					return new Respuesta("Ya Existe un Proveedor con el Número de Teléfono "+proveedorView.getNumTelefono(),false,null);		
				break;

			case Herramientas.tipoOperacion.actualizar:
				
				if(proveedorView.getId_Proveedor().equals("") || proveedorView.getId_Proveedor().isEmpty()) {
					return new Respuesta("No se ha seleccionado un Proveedor",false,null);
				}			
				
				respuestaT =  proveedoresDAO.obtenerProveedorIdproveedor(  proveedorView.getId_Proveedor());		
				if(!respuestaT.getValor())
					return respuestaT;

				if(respuestaT.getRespuesta() == null)
					return new Respuesta("No Existe un Proveedor con el Nombre "+proveedorView.getNombre()+" y Número de telefono"
							+ " "+proveedorView.getNumTelefono(),false,null);
				break;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Respuesta("Error : "+e.getMessage(),false,null);
		}		
		
		if(proveedorView.getNombre().equals("") || proveedorView.getNombre().isEmpty())
			return new Respuesta("Introduzca un Nombre de Proveedor Válido",false,null);
		
		if(proveedorView.getApaterno().equals("") || proveedorView.getApaterno().isEmpty())
			return new Respuesta("Introduzca un Apellido Paterno Válido",false,null);
		
		if(proveedorView.getAmaterno().equals("") || proveedorView.getAmaterno().isEmpty())
			return new Respuesta("Introduzca un Apellido Materno Válido",false,null);
		
		if(proveedorView.getCorreo().equals("") || proveedorView.getCorreo().isEmpty())
			return new Respuesta("Introduzca un Correo Electrónico Válido",false,null);
		
		if(proveedorView.getEmpresa().equals("") || proveedorView.getEmpresa().isEmpty())
			return new Respuesta("Introduzca un Nombre de Empresa Válido",false,null);
		
		if(proveedorView.getDireccion().equals("") || proveedorView.getDireccion().isEmpty())
			return new Respuesta("Introduzca una Dirección Válido",false,null);
		
		if( proveedorView.getFechaRegistro()== null|| proveedorView.getFechaRegistro().toString().isEmpty() )
			return new Respuesta("Introduzca un Fecha Válida",false,null);		
		
		return respuesta;
	}
	
}
