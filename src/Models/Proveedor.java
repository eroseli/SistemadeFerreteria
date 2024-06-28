package Models;

import java.sql.Date;

public class Proveedor {

	private int Id_Proveedor;
	private String Nombre;
	private String Apaterno;
	private String Amaterno;
	private String Num_Telefono;
	private String Correo;
	private String Empresa;
	private String Direccion;
	private Date FechaRegistro;
	
	public Proveedor() {
		
	}

	public Proveedor(int id_Proveedor, String nombre, String apaterno, String amaterno, String num_Telefono,
			String correo, String empresa, String direccion, Date fechaRegistro) {
		super();
		Id_Proveedor = id_Proveedor;
		Nombre = nombre;
		Apaterno = apaterno;
		Amaterno = amaterno;
		Num_Telefono = num_Telefono;
		Correo = correo;
		Empresa = empresa;
		Direccion = direccion;
		FechaRegistro = fechaRegistro;
	}

	public int getId_Proveedor() {
		return Id_Proveedor;
	}

	public void setId_Proveedor(int id_Proveedor) {
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

	public String getNum_Telefono() {
		return Num_Telefono;
	}

	public void setNum_Telefono(String num_Telefono) {
		Num_Telefono = num_Telefono;
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
	
	
	
}
