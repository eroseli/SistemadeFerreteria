package Models;

public class Respuesta {

	private String mensaje;
	private Boolean valor;
	private Object respuesta;
	
	
	
	public Respuesta() {
	}


	public Respuesta(String mensaje, Boolean valor, Object respuesta) {
		this.mensaje = mensaje;
		this.valor = valor;
		this.respuesta = respuesta;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Boolean getValor() {
		return valor;
	}


	public void setValor(Boolean valor) {
		this.valor = valor;
	}


	public Object getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
	
	
}
