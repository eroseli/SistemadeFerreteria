package DAO.ModelsDAO;

import java.sql.Date;

public class MarcaDAO {
	

	private int idMarca;            // Id de la marca
    private String nombre;          // Nombre de la marca
    private String descripcion;     // Descripción de la marca
    private String estatus;         // Estado de la marca (activa/inactiva)
    private Date fechaCreacion;     // Fecha de creación
    private Date fechaActualizacion;  // Fecha de actualización

    // Constructor vacío
    public  MarcaDAO() {
		// TODO Auto-generated constructor stub
	}

    // Constructor con parámetros
    public MarcaDAO(int idMarca, String nombre, String descripcion, String estatus, 
                 Date fechaCreacion, Date fechaActualizacion) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    // Método toString para representar el objeto de forma legible
    @Override
    public String toString() {
        return "Marca [idMarca=" + idMarca + ", nombre=" + nombre + ", descripcion=" + descripcion + 
               ", estatus=" + estatus + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
    }
	
}
