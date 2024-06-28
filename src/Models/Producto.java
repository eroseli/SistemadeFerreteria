package Models;

import java.sql.Date;

public class Producto {
	
	private int Id_producto;
	private String Codigo;
	private String Nombre;
	private String Descripcion;
	private String Cantidad;
	private Date Fecha_caducidad;
	private Float P_publico;
	private Float P_Mayoreo;
	private Float P_Adquisicion;
	private int Existencia;
	private String Categoria;
	private String Marca;
	
	public Producto() {
	}

	public Producto(int id_producto, String codigo, String nombre, String descripcion, String cantidad,
			Date fecha_caducidad, Float p_publico, Float p_Mayoreo, Float p_Adquisicion, int existencia,
			String categoria, String marca) {
		Id_producto = id_producto;
		Codigo = codigo;
		Nombre = nombre;
		Descripcion = descripcion;
		Cantidad = cantidad;
		Fecha_caducidad = fecha_caducidad;
		P_publico = p_publico;
		P_Mayoreo = p_Mayoreo;
		P_Adquisicion = p_Adquisicion;
		Existencia = existencia;
		Categoria = categoria;
		Marca = marca;
	}

	public int getId_producto() {
		return Id_producto;
	}

	public void setId_producto(int id_producto) {
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

	public Float getP_publico() {
		return P_publico;
	}

	public void setP_publico(Float p_publico) {
		P_publico = p_publico;
	}

	public Float getP_Mayoreo() {
		return P_Mayoreo;
	}

	public void setP_Mayoreo(Float p_Mayoreo) {
		P_Mayoreo = p_Mayoreo;
	}

	public Float getP_Adquisicion() {
		return P_Adquisicion;
	}

	public void setP_Adquisicion(Float p_Adquisicion) {
		P_Adquisicion = p_Adquisicion;
	}

	public int getExistencia() {
		return Existencia;
	}

	public void setExistencia(int existencia) {
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
