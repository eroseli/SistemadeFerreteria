package Models;

import java.sql.Date;

public class Empresa {
	
	public int idEmpresa;
	public String nombre;
	public String direccion;
	public String nombrePropietario;
	public String apaternoPropietario;
	public String amaternoPropietario;
	public String telefono;
	public String telefono2;
	public String correo;
	public Date fecha;
	
	public Empresa() {}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getApaternoPropietario() {
		return apaternoPropietario;
	}

	public void setApaternoPropietario(String apaternoPropietario) {
		this.apaternoPropietario = apaternoPropietario;
	}

	public String getAmaternoPropietario() {
		return amaternoPropietario;
	}

	public void setAmaternoPropietario(String amaternoPropietario) {
		this.amaternoPropietario = amaternoPropietario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
