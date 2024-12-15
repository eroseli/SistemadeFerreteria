package DAO.ModelsDAO;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class AperturaCaja {
	
	private int idApertura;
    private Timestamp fechaApertura;
    private Timestamp fechaCierre;
    private BigDecimal montoApertura;
    private BigDecimal montoCierre;
    private String observacionesEntrada;
    private String observacionesCierre;
    private String estado;
    private int idUsuario;
    
    public AperturaCaja() {}
    
    public AperturaCaja(
			int idApertura, 
			Timestamp fechaApertura,
			Timestamp fechaCierre,
			BigDecimal montoApertura, 
			BigDecimal montoCierre,
			String observacionesApertura,
			String observacionesCierre,
			String estado,
			int idUsuairo
		)
    {
		this.idApertura = idApertura;
		this.fechaApertura =fechaApertura;
		this.fechaCierre = fechaCierre;
		this.montoApertura = montoApertura;
		this.montoCierre = montoCierre;
		this.observacionesEntrada = observacionesApertura;
		this.observacionesCierre = observacionesCierre;
		this.estado = estado;
		this.idUsuario = idUsuairo;
	}
	
	public int getIdApertura() {
		return idApertura;
	}
	public void setIdApertura(int idApertura) {
		this.idApertura = idApertura;
	}
	public Timestamp getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Timestamp fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public BigDecimal getMontoApertura() {
		return montoApertura;
	}
	public void setMontoApertura(BigDecimal montoApertura) {
		this.montoApertura = montoApertura;
	}
	public BigDecimal getMontoCierre() {
		return montoCierre;
	}
	public void setMontoCierre(BigDecimal montoCierre) {
		this.montoCierre = montoCierre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getObservaciones() {
		return observacionesEntrada;
	}

	public void setObservaciones(String observaciones) {
		this.observacionesEntrada = observaciones;
	}

	public Timestamp getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Timestamp fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getObservacionesEntrada() {
		return observacionesEntrada;
	}

	public void setObservacionesEntrada(String observacionesEntrada) {
		this.observacionesEntrada = observacionesEntrada;
	}

	public String getObservacionesCierre() {
		return observacionesCierre;
	}

	public void setObservacionesCierre(String observacionesCierre) {
		this.observacionesCierre = observacionesCierre;
	} 	
	
}
