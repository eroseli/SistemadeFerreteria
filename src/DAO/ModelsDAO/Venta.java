package DAO.ModelsDAO;

import java.sql.Date;
import java.sql.Time;

public class Venta {
	
	public int consecutivo;
	public int idVenta;
	public String usuario;
	public String cliente;
	public int productos;
	public float tarjeta;
	public float efectivo;
	public int descuento;
	public Date fecha;
	public Time hora;

	public int getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getProductos() {
		return productos;
	}

	public void setProductos(int productos) {
		this.productos = productos;
	}

	public float getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(float tarjeta) {
		this.tarjeta = tarjeta;
	}

	public float getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(float efectivo) {
		this.efectivo = efectivo;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Venta() {
		
	}

	public Venta(int consecutivo, int idVenta, String usuario, String cliente, int productos, float tarjeta,
			float efectivo, int descuento, Date fecha, Time hora) {
		super();
		this.consecutivo = consecutivo;
		this.idVenta = idVenta;
		this.usuario = usuario;
		this.cliente = cliente;
		this.productos = productos;
		this.tarjeta = tarjeta;
		this.efectivo = efectivo;
		this.descuento = descuento;
		this.fecha = fecha;
		this.hora = hora;
	}
	
}
