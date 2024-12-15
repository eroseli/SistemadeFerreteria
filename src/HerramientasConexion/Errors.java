package HerramientasConexion;

public class Errors {

	public static String errors(int codeError) {
		
		String message = "";
		
		switch (codeError) {
			case 1062: //valor duplicado
				message =  "El o los registros que desea actualizar o insertar ya existen, valide su información";
				break;
			default:
				message = "Error no Identificado";
		}
		
		return message;
		
	}
	
}
