package conexionRed;

import java.util.Date;

public class Mensaje {
	private String nickname, mensaje, destino;
	private Date fecha;

	public Mensaje(String nickname, String mensaje, String destino, Date fecha) {
		this.nickname = nickname;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.destino = destino;
	}

	public String getNickname() {
		return nickname;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public String getDestino() {
		return this.destino;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
