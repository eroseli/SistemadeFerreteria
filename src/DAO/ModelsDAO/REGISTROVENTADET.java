package DAO.ModelsDAO;

import java.util.Date;


public class REGISTROVENTADET {

	private int Param_Id_Producto;
	private int Param_NumProductos;
	private int Param_Id_Venta;
	private float Param_Precio;
	private String Param_DescuentoM;
	private int Param_DescuentoEsp;
	private String Param_Id_Cliente;
	private int Param_respuesta;
	private String Param_mensaje;
	
	
	public  REGISTROVENTADET() {}
	
	public REGISTROVENTADET(int param_Id_Producto, int param_NumProductos, int param_Id_Venta, float param_Precio,
			String param_DescuentoM, int param_DescuentoEsp, String param_Id_Cliente, int param_respuesta, String param_mensaje) {
		Param_Id_Producto = param_Id_Producto;
		Param_NumProductos = param_NumProductos;
		Param_Id_Venta = param_Id_Venta;
		Param_Precio = param_Precio;
		Param_DescuentoM = param_DescuentoM;
		Param_DescuentoEsp = param_DescuentoEsp;
		Param_Id_Cliente = param_Id_Cliente;
		Param_respuesta = param_respuesta;
		Param_mensaje = param_mensaje;
	}

	public int getParam_Id_Producto() {
		return Param_Id_Producto;
	}

	public void setParam_Id_Producto(int param_Id_Producto) {
		Param_Id_Producto = param_Id_Producto;
	}

	public int getParam_NumProductos() {
		return Param_NumProductos;
	}

	public void setParam_NumProductos(int param_NumProductos) {
		Param_NumProductos = param_NumProductos;
	}

	public int getParam_Id_Venta() {
		return Param_Id_Venta;
	}

	public void setParam_Id_Venta(int param_Id_Venta) {
		Param_Id_Venta = param_Id_Venta;
	}

	public float getParam_Precio() {
		return Param_Precio;
	}

	public void setParam_Precio(float param_Precio) {
		Param_Precio = param_Precio;
	}

	public String getParam_DescuentoM() {
		return Param_DescuentoM;
	}

	public void setParam_DescuentoM(String param_DescuentoM) {
		Param_DescuentoM = param_DescuentoM;
	}

	public int getParam_DescuentoEsp() {
		return Param_DescuentoEsp;
	}

	public void setParam_DescuentoEsp(int param_DescuentoEsp) {
		Param_DescuentoEsp = param_DescuentoEsp;
	}

	public String getParam_Id_Cliente() {
		return Param_Id_Cliente;
	}

	public void setParam_Id_Cliente(String param_Id_Cliente) {
		Param_Id_Cliente = param_Id_Cliente;
	}

	public int getParam_respuesta() {
		return Param_respuesta;
	}

	public void setParam_respuesta(int param_respuesta) {
		Param_respuesta = param_respuesta;
	}

	public String getParam_mensaje() {
		return Param_mensaje;
	}

	public void setParam_mensaje(String param_mensaje) {
		Param_mensaje = param_mensaje;
	}
	
	
	
}
