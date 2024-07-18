package Models;

import java.sql.Date;

public class ProductoBusquedaView {
	
	private String busqueda;
	private Date fechaInicio;
	private Date fechaFinal;
	private String filtroBusqueda;

	public ProductoBusquedaView() {
	}
	
	public ProductoBusquedaView(String busqueda, Date fechaInicial, Date fechaFinal, String filtroBusqueda) {
		this.busqueda = busqueda;
		this.fechaInicio = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.filtroBusqueda = filtroBusqueda;
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
	
	
	
}
