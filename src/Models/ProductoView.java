package Models;

import java.sql.Date;

public class ProductoView {
	
	private String Id_producto;
	private String Codigo;
	private String Nombre;
	private String Descripcion;
	private String Cantidad;
	private Date Fecha_caducidad;
	private String P_publico;
	private String P_Mayoreo;
	private String P_Adquisicion;
	private String  Existencia;
	private String Categoria;
	private String Marca;
	

	public ProductoView() {}


	public String getId_producto() {
		return Id_producto;
	}


	public void setId_producto(String id_producto) {
		Id_producto = id_producto;
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


	public String getCantidad() {
		return Cantidad;
	}


	public void setCantidad(String cantidad) {
		Cantidad = cantidad;
	}


	public Date getFecha_caducidad() {
		return Fecha_caducidad;
	}


	public void setFecha_caducidad(Date fecha_caducidad) {
		Fecha_caducidad = fecha_caducidad;
	}


	public String getP_publico() {
		return P_publico;
	}


	public void setP_publico(String p_publico) {
		P_publico = p_publico;
	}


	public String getP_Mayoreo() {
		return P_Mayoreo;
	}


	public void setP_Mayoreo(String p_Mayoreo) {
		P_Mayoreo = p_Mayoreo;
	}


	public String getP_Adquisicion() {
		return P_Adquisicion;
	}


	public void setP_Adquisicion(String p_Adquisicion) {
		P_Adquisicion = p_Adquisicion;
	}


	public String getExistencia() {
		return Existencia;
	}


	public void setExistencia(String existencia) {
		Existencia = existencia;
	}


	public String getCategoria() {
		return Categoria;
	}


	public void setCategoria(String categoria) {
		Categoria = categoria;
	}


	public String getMarca() {
		return Marca;
	}


	public void setMarca(String marca) {
		Marca = marca;
	}
	
	
	
}
