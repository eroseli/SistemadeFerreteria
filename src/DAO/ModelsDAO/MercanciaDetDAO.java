package DAO.ModelsDAO;

public class MercanciaDetDAO {

	private int Id_HistorialCompraDet;
	private int Id_HistorialCompra;
	private int Id_Producto;
	private int Cantidad;
	private float PrecioUnitario;
	private float PrecioTotal;
	private String Estatus;
	
	public MercanciaDetDAO() {
		super();
	}

	public MercanciaDetDAO(int id_HistorialCompraDet, int id_HistorialCompra, int id_Producto, int cantidad,
			float precioUnitario, float precioTotal, String estatus) {
		super();
		Id_HistorialCompraDet = id_HistorialCompraDet;
		Id_HistorialCompra = id_HistorialCompra;
		Id_Producto = id_Producto;
		Cantidad = cantidad;
		PrecioUnitario = precioUnitario;
		PrecioTotal = precioTotal;
		Estatus = estatus;
	}
	
	public int getId_HistorialCompraDet() {
		return Id_HistorialCompraDet;
	}
	public void setId_HistorialCompraDet(int id_HistorialCompraDet) {
		Id_HistorialCompraDet = id_HistorialCompraDet;
	}
	public int getId_HistorialCompra() {
		return Id_HistorialCompra;
	}
	public void setId_HistorialCompra(int id_HistorialCompra) {
		Id_HistorialCompra = id_HistorialCompra;
	}
	public int getId_Producto() {
		return Id_Producto;
	}
	public void setId_Producto(int id_Producto) {
		Id_Producto = id_Producto;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public float getPrecioUnitario() {
		return PrecioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		PrecioUnitario = precioUnitario;
	}
	public float getPrecioTotal() {
		return PrecioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		PrecioTotal = precioTotal;
	}

	public String getEstatus() {
		return Estatus;
	}

	public void setEstatus(String estatus) {
		Estatus = estatus;
	}
	
	
	
}
