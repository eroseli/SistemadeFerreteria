package DAO.ModelsDAO;

public class REGISTROMERCANCIA {
	
	
	private int Param_Id_Producto;
	private int Param_Id_Proveedor;
	private int Param_Cantidad;
	private float Param_Total;
	private String Param_Tipo;
	private float Param_Precio_Unitario;
	
	public REGISTROMERCANCIA() {
		super();
	}		

	public REGISTROMERCANCIA(int param_Id_Producto, int param_Id_Proveedor, int param_Cantidad, float param_Total,
			String param_Tipo, float param_Precio_Unitario) {
		Param_Id_Producto = param_Id_Producto;
		Param_Id_Proveedor = param_Id_Proveedor;
		Param_Cantidad = param_Cantidad;
		Param_Total = param_Total;
		Param_Tipo = param_Tipo;
		Param_Precio_Unitario = param_Precio_Unitario;
	}



	public int getParam_Id_Producto() {
		return Param_Id_Producto;
	}

	public void setParam_Id_Producto(int param_Id_Producto) {
		Param_Id_Producto = param_Id_Producto;
	}

	public int getParam_Id_Proveedor() {
		return Param_Id_Proveedor;
	}

	public void setParam_Id_Proveedor(int param_Id_Proveedor) {
		Param_Id_Proveedor = param_Id_Proveedor;
	}

	public int getParam_Cantidad() {
		return Param_Cantidad;
	}

	public void setParam_Cantidad(int param_Cantidad) {
		Param_Cantidad = param_Cantidad;
	}

	public float getParam_Total() {
		return Param_Total;
	}

	public void setParam_Total(float param_Total) {
		Param_Total = param_Total;
	}

	public String getParam_Tipo() {
		return Param_Tipo;
	}

	public void setParam_Tipo(String param_Tipo) {
		Param_Tipo = param_Tipo;
	}

	public float getParam_Precio_Unitario() {
		return Param_Precio_Unitario;
	}

	public void setParam_Precio_Unitario(float param_Precio_Unitario) {
		Param_Precio_Unitario = param_Precio_Unitario;
	}
	
	
	

}
