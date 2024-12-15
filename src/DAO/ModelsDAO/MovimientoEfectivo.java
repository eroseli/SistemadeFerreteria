package DAO.ModelsDAO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MovimientoEfectivo {

    private int idMovimientoEfectivo;
    private String tipoTransaccion; // 'ENTRADA' o 'SALIDA'
    private BigDecimal monto; // Monto de la transacción
    private String concepto; // Descripción de la transacción
    private Timestamp fechaRegistro; // Fecha y hora de la transacción
    private String idUsuario; // Persona o entidad responsable de la transacción
    private String metodoPago; // 'efectivo', 'transferencia', 'tarjeta', 'otro'
    private String observaciones; // Detalles adicionales
    private String estadoMovimiento; // 'PENDIENTE' o 'COMPLETADO'
    private String estatus; // 'ACTIVO' o 'INACTIVO'

    // Constructor vacío
    public MovimientoEfectivo() {}

    // Constructor con todos los campos (excepto id, ya que es autoincrementable)
    public MovimientoEfectivo(String tipoTransaccion, BigDecimal monto, String concepto, Timestamp fechaRegistro, 
                               String idUsuario, String metodoPago, String observaciones, 
                               String estadoMovimiento, String estatus) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.concepto = concepto;
        this.fechaRegistro = fechaRegistro;
        this.idUsuario = idUsuario;
        this.metodoPago = metodoPago;
        this.observaciones = observaciones;
        this.estadoMovimiento = estadoMovimiento;
        this.estatus = estatus;
    }

    // Getters y Setters

    public int getIdMovimientoEfectivo() {
        return idMovimientoEfectivo;
    }

    public void setIdMovimientoEfectivo(int idMovimientoEfectivo) {
        this.idMovimientoEfectivo = idMovimientoEfectivo;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(String estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "MovimientoEfectivo{" +
                "idMovimientoEfectivo=" + idMovimientoEfectivo +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", monto=" + monto +
                ", concepto='" + concepto + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", idUsuario='" + idUsuario + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estadoMovimiento='" + estadoMovimiento + '\'' +
                ", estatus='" + estatus + '\'' +
                '}';
    }
}
