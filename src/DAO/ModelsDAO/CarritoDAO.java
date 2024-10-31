package DAO.ModelsDAO;

import java.sql.Date;
import java.sql.Time;

public class CarritoDAO {
	
	private int IdCarrito;
	private String Nombre;
	private int IdCliente;
	private Date fechaRegistro;
	private Time  hora;
	
	
	public CarritoDAO() {}


	public CarritoDAO(int idCarrito, String nombre, int idCliente, Date fechaRegistro, Time hora) {
		super();
		IdCarrito = idCarrito;
		Nombre = nombre;
		IdCliente = idCliente;
		this.fechaRegistro = fechaRegistro;
		this.hora = hora;
	}



	public int getIdCarrito() {
		return IdCarrito;
	}


	public void setIdCarrito(int idCarrito) {
		IdCarrito = idCarrito;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public int getIdCliente() {
		return IdCliente;
	}


	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}


	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	public Time getHora() {
		return hora;
	}


	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	

}
