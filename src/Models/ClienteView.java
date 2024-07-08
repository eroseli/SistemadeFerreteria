package Models;

import java.sql.Date;

public class ClienteView {
	
	private String Id_Cliente;
	private String Nombre;
	private String Apaterno;
	private String Amaterno;
	private String Identificador;
	private Date FechaNac;
	private String Telefono;
	private String Correo;
	private String Compras;
	
	public void ClienteView() {};
	
	public String getId_Cliente() {
		return Id_Cliente;
	}
	public void setId_Cliente(String id_Cliente) {
		Id_Cliente = id_Cliente;
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
	public String getCompras() {
		return Compras;
	}
	public void setCompras(String compras) {
		Compras = compras;
	}
	
	
	
	
}
