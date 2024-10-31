package DAO.ModelsDAO;

import java.sql.Date;

public class ProductosVentaDAO {
	
	private int IdHistorial;
	private String Codigo;
	private String Nombre;
	private String Descripcion;
	private int Cantidad;
	private float Precio;
	private float Monto;
	private String DescuentoM;
	private String DescuentoEsp;
	private Date FechaRegistro;
	
	public ProductosVentaDAO() {}
	
	public ProductosVentaDAO(int idHistorial, String codigo, String nombre, String descripcion, int cantidad, float precio,
			float monto, String descuentoM, String descuentoEsp, Date fechaRegistro) {
		super();
		IdHistorial = idHistorial;
		Codigo = codigo;
		Nombre = nombre;
		Descripcion = descripcion;
		Cantidad = cantidad;
		Precio = precio;
		Monto = monto;
		DescuentoM = descuentoM;
		DescuentoEsp = descuentoEsp;
		FechaRegistro = fechaRegistro;
	}

	public int getIdHistorial() {
		return IdHistorial;
	}

	public void setIdHistorial(int idHistorial) {
		IdHistorial = idHistorial;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public float getPrecio() {
		return Precio;
	}

	public void setPrecio(float precio) {
		Precio = precio;
	}

	public float getMonto() {
		return Monto;
	}

	public void setMonto(float monto) {
		Monto = monto;
	}

	public String getDescuentoM() {
		return DescuentoM;
	}

	public void setDescuentoM(String descuentoM) {
		DescuentoM = descuentoM;
	}

	public String getDescuentoEsp() {
		return DescuentoEsp;
	}

	public void setDescuentoEsp(String descuentoEsp) {
		DescuentoEsp = descuentoEsp;
	}

	public Date getFechaRegistro() {
		return FechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}

}
