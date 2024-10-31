package DAO.ModelsDAO;

import java.sql.Date;

public class Usuario {

	private int Id_Usuario;
	private String usuario;
	private String password;
	private String nombre;
	private String apaterno;
	private String amaterno;
	private String correo;
	private String direccion;
	private String puesto;
	private String telefono;
	private Date fechaRegistro;
	
	public Usuario() {
	}

	public Usuario(int id_Usuario, String usuario, String password, String nombre, String apaterno, String amaterno,
			String correo, String direccion, String puesto, String telefono,Date fechaRegistro) {
		Id_Usuario = id_Usuario;
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.apaterno = apaterno;
		this.amaterno = amaterno;
		this.correo = correo;
		this.direccion = direccion;
		this.puesto = puesto;
		this.telefono = telefono;
		this.fechaRegistro = fechaRegistro;
	}

	public int getId_Usuario() {
		return Id_Usuario;
	}

	public void setId_Usuario(int id_Usuario) {
		Id_Usuario = id_Usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApaterno() {
		return apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public String getAmaterno() {
		return amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
}
