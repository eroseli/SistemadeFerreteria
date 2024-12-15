package Services;

import DAO.ClientesDAO;
import DAO.ProductosDAO;
import DAO.ModelsDAO.Cliente;
import HerramientasConexion.Herramientas;
import HerramientasConexion.Herramientas.cadenas;
import Models.ClienteView;
import Models.Respuesta;

public class ClienteService {
	
	private Respuesta respuesta;
	private ClientesDAO clienteDao;
	
	public Respuesta insertar(ClienteView clienteView) {
		
		respuesta = new Respuesta("",true,null);
		
		respuesta = validarCliente(Herramientas.tipoOperacion.insertar, clienteView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = TraducirRespuesta(clienteView, Herramientas.tipoOperacion.insertar);
		if(!respuesta.getValor())
			return respuesta;
		
		respuesta = realizarInsercion((Cliente) respuesta.getRespuesta());
		return respuesta;
	}
	
	public Respuesta realizarInsercion(Cliente cliente) {
		respuesta = new Respuesta("",true,null);
		clienteDao = new ClientesDAO();
		respuesta = clienteDao.insertarCliente(cliente);
		return respuesta;
	}
	
	public Respuesta actualizar(ClienteView clienteView) {
		
		respuesta = new Respuesta("",true,null);
		
		respuesta = validarCliente(Herramientas.tipoOperacion.actualizar, clienteView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = TraducirRespuesta(clienteView, Herramientas.tipoOperacion.actualizar);
		if(!respuesta.getValor())
			return respuesta;
		
		respuesta = realizarActualizacion((Cliente) respuesta.getRespuesta());
		return respuesta;
	}
	
	public Respuesta realizarActualizacion(Cliente cliente) {
		respuesta = new Respuesta("",true,null);
		clienteDao = new ClientesDAO();
		respuesta = clienteDao.actualizarCliente(cliente);
		return respuesta;
	}
	

	public Respuesta eliminar(String idCliente) {
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaT = new Respuesta("",false,null);
		
		ClientesDAO clientesDAO = new ClientesDAO();
		respuesta = clientesDAO.eliminarCliente(idCliente);
		
		respuestaT = clientesDAO.obtenerCliente(idCliente, "");

		if ( !(respuestaT.getRespuesta() == null) )
			return new Respuesta("Problemas al intentar Eliminar al Cliente "+idCliente,false,null);
		
		return respuesta;
	}
	
	public Respuesta seleccionar(String nombre) {
		
		respuesta = new Respuesta("",true,null);
		ClientesDAO dao = new ClientesDAO();
		
		if (  nombre== null || nombre.isEmpty() || nombre.equals("")) {
			return dao.obtenerClientes();
		}
		return dao.obtenerClienteDescripcion(nombre);
	}
	
	public Respuesta TraducirRespuesta(ClienteView clienteView, int tipoOperacion) {
		
		respuesta = new Respuesta("",true,null);
		Cliente cliente = new Cliente();
		try {
			
			cliente.setNombre(clienteView.getNombre());
			cliente.setApaterno(clienteView.getApaterno());
			cliente.setAmaterno(clienteView.getAmaterno());
			cliente.setFechaNac(clienteView.getFechaNac());
			cliente.setTelefono(clienteView.getTelefono());
			cliente.setCorreo(clienteView.getCorreo());
			cliente.setCompras(Integer.parseInt(clienteView.getCompras()));
			
			if (tipoOperacion == Herramientas.tipoOperacion.insertar) {
				respuesta  = generalIdentificador(cliente);
				if (!respuesta.getValor())
					return respuesta;
				cliente.setIdentificador(respuesta.getRespuesta().toString());
			}	
			else
				cliente.setIdentificador(clienteView.getId_Cliente());
			
			respuesta.setRespuesta(cliente);
		} catch (NumberFormatException e) {
			return new Respuesta("Error al intentar Traducir valores : "+e.getMessage(),false,null);
		}
		
		return respuesta;
	}
	
	public Respuesta generalIdentificador(Cliente cliente) {
		
		clienteDao = new ClientesDAO();
		respuesta = new Respuesta("",true,null);
		String idCliente = "";
		
		try {
			
			respuesta = clienteDao.obtenerCantidadClientes();
			if(!respuesta.getValor())
				return new Respuesta ("Error al intentar Generar Identificador del Cliente"+respuesta.getMensaje(),false,null);
			
			idCliente = idCliente + cliente.getApaterno().substring(0,2);
			idCliente = idCliente + cliente.getAmaterno().charAt(0);
			idCliente = idCliente + cliente.getNombre().charAt(0);
			idCliente = idCliente + cliente.getFechaNac().getDate();
			idCliente = idCliente + respuesta.getRespuesta().toString();
			respuesta.setRespuesta(idCliente);
		} catch (Exception e) {
			return new Respuesta ("Error al intentar Generar Identificador del Cliente "+e.getMessage(),false,null);
		}
		
		return respuesta;
	}
	
	public Respuesta validarCliente(int tipoOperacion, ClienteView clienteView) {
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaT = new Respuesta("",false,null);
		clienteDao = new ClientesDAO();
		try {
			
			//Para actualizar
			if( Herramientas.tipoOperacion.actualizar == tipoOperacion && (clienteView.getId_Cliente().equals("") || clienteView.getId_Cliente().isEmpty()))
				return new Respuesta("No se ha seleccionado un Cliente",false,null);	
			else if (Herramientas.tipoOperacion.insertar == tipoOperacion)
				clienteView.setId_Cliente("0");
			respuestaT =  clienteDao.obtenerCliente( clienteView.getId_Cliente(), clienteView.getTelefono());		
			
			if(!respuestaT.getValor())
				return respuestaT;
			
			//tipo de Operacion
			switch (tipoOperacion) {
			case Herramientas.tipoOperacion.insertar:
				if(respuestaT.getRespuesta() != null)
					return new Respuesta("Ya Existe un Cliente con el Nombre "+clienteView.getNombre() +" o el número de teléfono "+clienteView.getTelefono(),false,null);
					
				break;

			case Herramientas.tipoOperacion.actualizar:
				if(respuestaT.getRespuesta() == null)
					return new Respuesta("No Existe un Cliente con el Nombre "+clienteView.getNombre(),false,null);
				break;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Respuesta("Error : "+e.getMessage(),false,null);
		}
		
		if(clienteView.getNombre().equals("") || clienteView.getNombre().isEmpty())
			return new Respuesta("Introduzca un Nombre de Usuario Válido",false,null);
		
		if(clienteView.getApaterno().equals("") || clienteView.getApaterno().isEmpty())
			return new Respuesta("Introduzca un Apellido Paterno Válido",false,null);
		
		if(clienteView.getAmaterno().equals("") || clienteView.getAmaterno().isEmpty())
			return new Respuesta("Introduzca un Apellido Materno Válido",false,null);
		
		if(clienteView.getFechaNac()== null|| clienteView.getFechaNac().toString().isEmpty() )
			return new Respuesta("Introduzca una Fecha de Nacimiento Válida",false,null);
		
		if (!Herramientas.validarFecha(clienteView.getFechaNac().toString())) 
			return new Respuesta("Introduzca una Fecha de Nacimiento con Formato Válido",false,null);
		
		if(clienteView.getTelefono().equals("") || clienteView.getTelefono().isEmpty())
			return new Respuesta("Introduzca un Teléfono Celular Válido",false,null);
		
		if(clienteView.getCompras().equals("") || clienteView.getCompras().isEmpty())
			return new Respuesta("Introduzca la cantidad de Compras en número",false,null);
		
		return respuesta;
	}
	
}
