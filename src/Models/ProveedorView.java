package Models;

import java.sql.Date;

public class ProveedorView {
	
	private String Id_Proveedor;
	private String Nombre;
	private String Apaterno;
	private String Amaterno;
	private String Telefono;
	private String Correo;
	private String Empresa;
	private String Direccion;
	private String TipoProducto;
	private String NotasAdicionales;
	private Date FechaRegistro;
	
	public ProveedorView() {
		
	}

	public String getId_Proveedor() {
		return Id_Proveedor;
	}

	public void setId_Proveedor(String id_Proveedor) {
		Id_Proveedor = id_Proveedor;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApaterno() {
		return Apaterno;
	}

	public void setApaterno(String apaterno) {
		Apaterno = apaterno;
	}

	public String getAmaterno() {
		return Amaterno;
	}

	public void setAmaterno(String amaterno) {
		Amaterno = amaterno;
	}

	public String getNumTelefono() {
		return Telefono;
	}

	public void setTelefono(String num_Telefono) {
		Telefono = num_Telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public Date getFechaRegistro() {
		return FechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}

	public String getTipoProducto() {
		return TipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		TipoProducto = tipoProducto;
	}

	public String getNotasAdicionales() {
		return NotasAdicionales;
	}

	public void setNotasAdicionales(String notasAdicionales) {
		NotasAdicionales = notasAdicionales;
	}
	
	
	
}
