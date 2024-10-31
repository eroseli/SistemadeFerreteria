package DAO.ModelsDAO;

import java.sql.Date;

public class VentaTotalesDAO {
	
	Date fechaInicio;
	Date fechaFinal;
	int totalVentas;
	int totalProductos;
	float montoTotalVenta;
	float descuentoAplicado;
	String productoEstrella;
	float ticketPromedio;
	
	public VentaTotalesDAO() {}

	public VentaTotalesDAO(Date fechaInicio, Date fechaFinal, 
							int totalVentas, int totalProductos,
							float montoTotalVenta, float descuentoAplicado, 
							String productoEstrella, float ticketPromedio) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.totalVentas = totalVentas;
		this.totalProductos = totalProductos;
		this.montoTotalVenta = montoTotalVenta;
		this.descuentoAplicado = descuentoAplicado;
		this.productoEstrella = productoEstrella;
		this.ticketPromedio = ticketPromedio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(int totalVentas) {
		this.totalVentas = totalVentas;
	}

	public int getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(int totalProductos) {
		this.totalProductos = totalProductos;
	}

	public float getMontoTotalVenta() {
		return montoTotalVenta;
	}

	public void setMontoTotalVenta(float montoTotalVenta) {
		this.montoTotalVenta = montoTotalVenta;
	}

	public float getDescuentoAplicado() {
		return descuentoAplicado;
	}

	public void setDescuentoAplicado(float descuentoAplicado) {
		this.descuentoAplicado = descuentoAplicado;
	}

	public String getProductoEstrella() {
		return productoEstrella;
	}

	public void setProductoEstrella(String productoEstrella) {
		this.productoEstrella = productoEstrella;
	}

	public float getTicketPromedio() {
		return ticketPromedio;
	}

	public void setTicketPromedio(float ticketPromedio) {
		this.ticketPromedio = ticketPromedio;
	}
	
	

}
