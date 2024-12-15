package DAO.ModelsDAO;

public class PuestoDAO {
	
	private int Id_puesto;
	private String Nombre;
	private String Descripcion;
	private String Estatus;
	private int Nivel_jerarquico;
	
	public PuestoDAO() {}

	public PuestoDAO(int id_puesto, String nombre, String descripcion, String estatus, int nivel_jerarquico) {
		super();
		Id_puesto = id_puesto;
		Nombre = nombre;
		Descripcion = descripcion;
		Estatus = estatus;
		Nivel_jerarquico = nivel_jerarquico;
	}

	public int getId_puesto() {
		return Id_puesto;
	}

	public void setId_puesto(int id_puesto) {
		Id_puesto = id_puesto;
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

	public String getEstatus() {
		return Estatus;
	}

	public void setEstatus(String estatus) {
		Estatus = estatus;
	}

	public int getNivel_jerarquico() {
		return Nivel_jerarquico;
	}

	public void setNivel_jerarquico(int nivel_jerarquico) {
		Nivel_jerarquico = nivel_jerarquico;
	}

	@Override
	public String toString() {
		return "PuestoDAO [Id_puesto=" + Id_puesto + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion
				+ ", Estatus=" + Estatus + ", Nivel_jerarquico=" + Nivel_jerarquico + "]";
	}
	
	

}
