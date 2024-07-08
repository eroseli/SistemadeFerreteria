package DAO.ModelsDAO;

public class REGISTROVENTA {
	
	private int Param_Id_Usuario;
    private String Param_Id_Cliente;
    private int Param_NumProductos;
    private float Param_Cantidad;  // Cantidad Total en efectivo
    private float Param_PagoTarjeta;
    private float Param_PagoEfectivo;
    private int Param_Descuento;
    private float Param_PagoCliente;
    private float Param_SubTotal;
	private int  Param_respuesta;
    private String  Param_mensaje;
    
    public REGISTROVENTA(){};
    
	public REGISTROVENTA(int param_Id_Usuario, String param_Id_Cliente, int param_NumProductos,
			float param_Cantidad, float param_PagoTarjeta, float param_PagoEfectivo, int param_Descuento,
			float param_PagoCliente, float param_SubTotal, int param_respuesta, String param_mensaje) {
		Param_Id_Usuario = param_Id_Usuario;
		Param_Id_Cliente = param_Id_Cliente;
		Param_NumProductos = param_NumProductos;
		Param_Cantidad = param_Cantidad;
		Param_PagoTarjeta = param_PagoTarjeta;
		Param_PagoEfectivo = param_PagoEfectivo;
		Param_Descuento = param_Descuento;
		Param_PagoCliente = param_PagoCliente;
		Param_SubTotal = param_SubTotal;
		Param_respuesta = param_respuesta;
		Param_mensaje = param_mensaje;
	}

	public int getParam_Id_Usuario() {
		return Param_Id_Usuario;
	}

	public void setParam_Id_Usuario(int param_Id_Usuario) {
		Param_Id_Usuario = param_Id_Usuario;
	}

	public String getParam_Id_Cliente() {
		return Param_Id_Cliente;
	}

	public void setParam_Id_Cliente(String param_Id_Cliente) {
		Param_Id_Cliente = param_Id_Cliente;
	}

	public int getParam_NumProductos() {
		return Param_NumProductos;
	}

	public void setParam_NumProductos(int param_NumProductos) {
		Param_NumProductos = param_NumProductos;
	}

	public float getParam_Cantidad() {
		return Param_Cantidad;
	}

	public void setParam_Cantidad(float param_Cantidad) {
		Param_Cantidad = param_Cantidad;
	}

	public float getParam_PagoTarjeta() {
		return Param_PagoTarjeta;
	}

	public void setParam_PagoTarjeta(float param_PagoTarjeta) {
		Param_PagoTarjeta = param_PagoTarjeta;
	}

	public float getParam_PagoEfectivo() {
		return Param_PagoEfectivo;
	}

	public void setParam_PagoEfectivo(float param_PagoEfectivo) {
		Param_PagoEfectivo = param_PagoEfectivo;
	}

	public int getParam_Descuento() {
		return Param_Descuento;
	}

	public void setParam_Descuento(int param_Descuento) {
		Param_Descuento = param_Descuento;
	}

	public float getParam_PagoCliente() {
		return Param_PagoCliente;
	}

	public void setParam_PagoCliente(float param_PagoCliente) {
		Param_PagoCliente = param_PagoCliente;
	}

	public float getParam_SubTotal() {
		return Param_SubTotal;
	}

	public void setParam_SubTotal(float param_SubTotal) {
		Param_SubTotal = param_SubTotal;
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
