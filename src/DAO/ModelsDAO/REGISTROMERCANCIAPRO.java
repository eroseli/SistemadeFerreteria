package DAO.ModelsDAO;
public class REGISTROMERCANCIAPRO {

    // Campos de la cabecera de la compra
    private int tipoOperacion; // 1 registro cabecera, 2 registro detalle
    private int idHistorialCompra;
    private String idProveedor;
    private int totalProductos;
    private float totalCompra;
    private String descripcion;
    private String estadoPago;
    private String tipo;

    // Campos del detalle de la compra de mercancía
    private int idProducto;
    private int cantidad;
    private float precioUnitario;
    private float precioTotal;

    // Parámetros de salida
    private int respuesta;
    private String mensaje;

    // Constructor vacío
    public REGISTROMERCANCIAPRO() {
        // Constructor vacío, no hace nada pero se necesita para crear objetos sin inicializar nada
    }

    // Constructor con todos los parámetros (constructor total)
    public REGISTROMERCANCIAPRO(int tipoOperacion, int idHistorialCompra, String idProveedor, int totalProductos,
                                float totalCompra, String descripcion, String estadoPago, String tipo,
                                int idProducto, int cantidad, float precioUnitario, float precioTotal,
                                int respuesta, String mensaje) {
        this.tipoOperacion = tipoOperacion;
        this.idHistorialCompra = idHistorialCompra;
        this.idProveedor = idProveedor;
        this.totalProductos = totalProductos;
        this.totalCompra = totalCompra;
        this.descripcion = descripcion;
        this.estadoPago = estadoPago;
        this.tipo = tipo;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.respuesta = respuesta;
        this.mensaje = mensaje;
    }

    // Getters y Setters

    public int getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public int getIdHistorialCompra() {
        return idHistorialCompra;
    }

    public void setIdHistorialCompra(int idHistorialCompra) {
        this.idHistorialCompra = idHistorialCompra;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(int totalProductos) {
        this.totalProductos = totalProductos;
    }

    public float getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(float totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    // Método para mostrar un resumen del objeto
    @Override
    public String toString() {
        return "REGISTROMERCANCIAPRO{" +
                "tipoOperacion=" + tipoOperacion +
                ", idHistorialCompra=" + idHistorialCompra +
                ", idProveedor='" + idProveedor + '\'' +
                ", totalProductos=" + totalProductos +
                ", totalCompra=" + totalCompra +
                ", descripcion='" + descripcion + '\'' +
                ", estadoPago='" + estadoPago + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", precioTotal=" + precioTotal +
                ", respuesta=" + respuesta +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
