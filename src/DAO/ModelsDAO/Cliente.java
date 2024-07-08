package DAO.ModelsDAO;

import java.sql.Date;

public class Cliente {

	private String Nombre;
	private String Apaterno;
	private String Amaterno;
	private String Identificador;
	private Date FechaNac;
	private String Telefono;
	private String Correo;
	private int Compras;
	
	public Cliente() {
		
	}
	
	public Cliente(String identificador, String nombre, String apaterno, String amaterno, Date fechaNac,
			String telefono, String correo, int compras) {
		Identificador = identificador;
		Nombre = nombre;
		Apaterno = apaterno;
		Amaterno = amaterno;
		FechaNac = fechaNac;
		Telefono = telefono;
		Correo = correo;
		Compras = compras;
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

	public String getIdentificador() {
		return Identificador;
	}

	public void setIdentificador(String identificador) {
		Identificador = identificador;
	}

	public Date getFechaNac() {
		return FechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		FechaNac = fechaNac;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public int getCompras() {
		return Compras;
	}

	public void setCompras(int compras) {
		Compras = compras;
	}
	
	
	
	
	
}
