package Services;

import DAO.ProductosDAO;
import DAO.UsuariosDAO;
import DAO.ModelsDAO.Usuario;
import HerramientasConexion.Herramientas;
import Models.ProductoView;
import Models.Respuesta;
import Models.UsuarioView;

public class UsuarioService {

	Respuesta respuesta;
	
	public Respuesta insertar(UsuarioView usuarioView) {
		
		respuesta = new Respuesta("",true,null);
	
		respuesta = validarUsuario(Herramientas.tipoOperacion.insertar, usuarioView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = TraducirDatos(usuarioView);
		if (!respuesta.getValor()) 
			return respuesta;
		
		respuesta = realizarInsercion((Usuario)respuesta.getRespuesta());
		return respuesta;
	}
	
	public Respuesta realizarInsercion(Usuario usuario) 
	{
		respuesta = new Respuesta("",true,null);
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		respuesta = usuariosDAO.insertarUsuario(usuario);
		return respuesta;		
	}
	
	public Respuesta actualizar(UsuarioView usuarioView) {
		
		respuesta = new Respuesta("",true,null);
		
		respuesta = validarUsuario(Herramientas.tipoOperacion.actualizar, usuarioView);
		if (!respuesta.getValor())
			return respuesta;
		
		respuesta = TraducirDatos(usuarioView);
		if (!respuesta.getValor())
			return respuesta;
		
		respuesta = realizarActualizacion((Usuario) respuesta.getRespuesta());
		
		return respuesta;
	}	
	
	public Respuesta realizarActualizacion(Usuario usuario) {
		respuesta = new Respuesta("",true,null);
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		respuesta = usuariosDAO.actualizarUsuario(usuario);
		return respuesta;	
	}
	
	public Respuesta eliminar(String IdUsuario) {
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaT = new Respuesta("",true,null);
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		try {
			int Id = Integer.parseInt(IdUsuario);
			respuesta = usuariosDAO.eliminarUsuario(Id);
			
			respuestaT = usuariosDAO.obtenerUsuario(Id);
			
			if(!(respuestaT.getRespuesta() == null) )
				return new Respuesta("Problemas al intentar Eliminar al Usuario ",false,null);
			
		} catch (NumberFormatException e) {
			return new Respuesta("Error "+e.getMessage(),false,null);
		}
		
		return respuesta;
	}
	
	public Respuesta selecciona(String nombre) {
		
		respuesta = new Respuesta("",true,null);
		UsuariosDAO dao = new UsuariosDAO();
		
		if (nombre.isEmpty() || nombre.equals("")) {
			return dao.obtenerUsuarios();
		}
		return dao.obtenerUsuarioDescripcion(nombre);
		
	}
	
	public Respuesta TraducirDatos(UsuarioView usuarioView) {
		
		respuesta = new Respuesta("",true,null);
		
		try {
			
			Usuario usuario = new Usuario();
			
			usuario.setId_Usuario(  usuarioView.getId_Usuario().isEmpty() || usuarioView.getId_Usuario().equals("") ?0: Integer.parseInt(usuarioView.getId_Usuario() ));
			usuario.setUsuario(usuarioView.getUsuario());
			usuario.setPassword(usuarioView.getPassword());
			usuario.setNombre(usuarioView.getNombre());
			usuario.setApaterno(usuarioView.getApaterno());
			usuario.setAmaterno(usuarioView.getAmaterno());
			usuario.setCorreo(usuarioView.getCorreo());
			usuario.setDireccion(usuarioView.getDireccion());
			usuario.setPuesto(usuarioView.getPuesto());
			usuario.setTelefono(usuarioView.getTelefono());
			
			respuesta.setRespuesta(usuario);
			
		} catch (Exception e) {
			return new Respuesta("Error : "+e.getMessage(),false,null);
		}
		return respuesta;
	}
	
	public Respuesta validarUsuario(int tipoOperacion, UsuarioView usuarioView) {
		
		respuesta = new Respuesta("",true,null);
		Respuesta respuestaT = new Respuesta("",true,null);
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		
		try {
			
			//Para actualizar
			if( Herramientas.tipoOperacion.actualizar == tipoOperacion && usuarioView.getId_Usuario().equals("") && usuarioView.getId_Usuario().isEmpty())
				return new Respuesta("No se ha seleccionado un Usuario",false,null);	
			respuestaT =  usuariosDAO.obtenerUsuarioNombre( usuarioView.getUsuario());		
			
			if(!respuestaT.getValor())
				return respuestaT;
			//tipo de Operacion
			switch (tipoOperacion) {
			case Herramientas.tipoOperacion.insertar:
				if(respuestaT.getRespuesta() != null)
					return new Respuesta("Ya Existe un Usuario con el Nombre de Usuario "+usuarioView.getUsuario(),false,null);
				break;

			case Herramientas.tipoOperacion.actualizar:
				if(respuestaT.getRespuesta() == null)
					return new Respuesta("No Existe un Usuario con el Nombre "+usuarioView.getUsuario(),false,null);
				break;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Respuesta("Error : "+e.getMessage(),false,null);
		}
		
				
		if(usuarioView.getUsuario().equals("") && usuarioView.getUsuario().isEmpty())
			return new Respuesta("Introduzca un Nombre de Usuario Válido",false,null);
		
		if(!usuarioView.getPassword().equals("") && !usuarioView.getPassword().isEmpty()) {
			if(usuarioView.getPassword().length()<8)
				return new Respuesta("La Contraseña debe Contener al menos 8 caracteres",false,null );
		}else {
			return new Respuesta("Introduzca una Contraseña Correcta",false,null );
		}
		
		if(!usuarioView.getValidarPassword().equals("") && !usuarioView.getValidarPassword().isEmpty()) {
			if(usuarioView.getValidarPassword().length()<8)
				return new Respuesta("La Contraseña debe Contener al menos 8 caracteres",false,null );
		}else {
			return new Respuesta("Introduzca una Contraseña Correcta",false,null );
		}
		
		if(!usuarioView.getPassword().equals(usuarioView.getValidarPassword()))
			return new Respuesta("Las Contraseñas No Coinciden",false,null);
		
		if(usuarioView.getNombre().equals("") && usuarioView.getNombre().isEmpty())
			return new Respuesta("Introduzca un Nombre Correcto",false,null );
		
		if(usuarioView.getApaterno().equals("") && usuarioView.getApaterno().isEmpty())
			return new Respuesta("Introduzca un Apellido Paterno Correcto",false,null);
		
		if(usuarioView.getAmaterno().equals("") && usuarioView.getAmaterno().isEmpty())
			return new Respuesta("Introduzca un Apellido Materno Correcto",false,null);
		
		if(usuarioView.getCorreo().equals("") && usuarioView.getCorreo().isEmpty())
			return new Respuesta("Introduzca un Correo Correcto",false,null);
		
		if(usuarioView.getDireccion().equals("") && usuarioView.getDireccion().isEmpty())
			return new Respuesta("Introduzca un Correo Correcto",false,null);
		
		if(usuarioView.getPuesto().equals("") && usuarioView.getPuesto().isEmpty())
			return new Respuesta("Introduzca un Puesto Correcto",false,null);
		
		if (usuarioView.getTelefono().equals("") && usuarioView.getTelefono().isEmpty()) {
			return new Respuesta("Introduzca un Teléfono Correcto",false,null);
		}
		
		return respuesta;
		
	}
	
}
