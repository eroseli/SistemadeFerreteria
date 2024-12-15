package Controllers;

import HerramientasConexion.Herramientas;
import Models.Empresa;
import Models.Respuesta;
import Services.EmpresaService;

public class ControladorEmpresa {
	
	public Respuesta proceso(int tipoOperacion, Object objeto) {
		
		Respuesta respuesta = new Respuesta("",true,null);
		EmpresaService empresaService = new EmpresaService();
		
		switch (tipoOperacion) {
		case Herramientas.tipoOperacion.seleccionar:
			respuesta = empresaService.obtenerEmpresa();
			break;
		case Herramientas.tipoOperacion.actualizar:
			respuesta = empresaService.actualizarEmpresa((Empresa) objeto);
			break;
		}
		
		return respuesta;
		
	}

}
