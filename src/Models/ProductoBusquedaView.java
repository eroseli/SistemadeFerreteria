package Models;

import java.sql.Date;

public class ProductoBusquedaView {
	
	private String busqueda;
	private Date fechaInicio;
	private Date fechaFinal;
	private String filtroBusqueda;
	private String fecha;

	public ProductoBusquedaView() {
	}
	
	public ProductoBusquedaView(String busqueda, Date fechaInicial, Date fechaFinal, String filtroBusqueda, String fecha) {
		this.busqueda = busqueda;
		this.fechaInicio = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.filtroBusqueda = filtroBusqueda;
		this.fecha = fecha;
	}
	
	public String getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
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
	public String getFiltroBusqueda() {
		return filtroBusqueda;
	}
	public void setFiltroBusqueda(String filtroBusqueda) {
		this.filtroBusqueda = filtroBusqueda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
