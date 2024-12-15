package DAO.ModelsDAO;

public class Categoria {
	
	private int id_cateogria;
	private String nombre;
	private String descripcion;
	private String  tipo_vehiculo;
	
	public Categoria() {}
	
	public Categoria(int id_cateogria, String nombre, String descripcion, String tipo_vehiculo) {
		super();
		this.id_cateogria = id_cateogria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo_vehiculo = tipo_vehiculo;
	}
	public int getId_cateogria() {
		return id_cateogria;
	}
	public void setId_cateogria(int id_cateogria) {
		this.id_cateogria = id_cateogria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}
	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}
	
	
	
	
}
