package Services;

import DAO.EmpresaDAO;
import Models.Empresa;
import Models.Respuesta;

public class EmpresaService {

	Respuesta respuesta = new Respuesta("",true,null);
	
	public Respuesta obtenerEmpresa() {
		respuesta = new Respuesta("",true,null);
		EmpresaDAO empresaDao = new EmpresaDAO();		
		return empresaDao.obtenerEmpresa();
	}
	
	public Respuesta actualizarEmpresa(Empresa empresa) {
		respuesta = new Respuesta("",true,null);
		EmpresaDAO empresaDAO = new EmpresaDAO();
		return empresaDAO.actualizarEmpresa(empresa);	
	}
	
}
