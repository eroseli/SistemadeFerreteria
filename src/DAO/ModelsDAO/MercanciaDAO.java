package DAO.ModelsDAO;

import java.sql.Date;

public class MercanciaDAO {

	private int Id_HistorialCompra;
	private String Id_Proveedor;
	private int TotalProductos;
	private float TotalCompra; 
	private String Descripcion;
	private String EstadoPago;
	private String TipoOperacion;
	private Date FechaRegistro;
	private String Estatus;
	
	public MercanciaDAO() {
		super();
	}
	public MercanciaDAO(int id_HistorialCompra, String id_Proveedor, int totalProductos, float totalCompra,
			String descripcion, String estadoPago, String tipoOperacion, Date fechaRegistro, String estatus) {
		super();
		Id_HistorialCompra = id_HistorialCompra;
		Id_Proveedor = id_Proveedor;
		TotalProductos = totalProductos;
		TotalCompra = totalCompra;
		Descripcion = descripcion;
		EstadoPago = estadoPago;
		TipoOperacion = tipoOperacion;
		FechaRegistro = fechaRegistro;
		Estatus = estatus;
	}
	public int getId_HistorialCompra() {
		return Id_HistorialCompra;
	}
	public void setId_HistorialCompra(int id_HistorialCompra) {
		Id_HistorialCompra = id_HistorialCompra;
	}
	public String getId_Proveedor() {
		return Id_Proveedor;
	}
	public void setId_Proveedor(String id_Proveedor) {
		Id_Proveedor = id_Proveedor;
	}
	public int getTotalProductos() {
		return TotalProductos;
	}
	public void setTotalProductos(int totalProductos) {
		TotalProductos = totalProductos;
	}
	public float getTotalCompra() {
		return TotalCompra;
	}
	public void setTotalCompra(float totalCompra) {
		TotalCompra = totalCompra;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getEstadoPago() {
		return EstadoPago;
	}
	public void setEstadoPago(String estadoPago) {
		EstadoPago = estadoPago;
	}
	public String getTipoOperacion() {
		return TipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		TipoOperacion = tipoOperacion;
	}
	public Date getFechaRegistro() {
		return FechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}
	public String getEstatus() {
		return Estatus;
	}
	public void setEstatus(String estatus) {
		Estatus = estatus;
	}
	
	
	
}
