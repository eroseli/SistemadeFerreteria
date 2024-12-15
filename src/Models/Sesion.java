package Models;

import DAO.ModelsDAO.Usuario;

public class Sesion {
	
    private static Sesion instancia;  // Instancia única de la sesión
	public Empresa empresa;
	public Usuario usuario;
	
	private Sesion() {}
	
	 // Método para obtener la instancia única de la sesión
    public static Sesion obtenerInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
