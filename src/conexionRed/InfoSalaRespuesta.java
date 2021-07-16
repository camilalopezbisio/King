package conexionRed;

import java.util.ArrayList;
import java.util.List;

public class InfoSalaRespuesta {
	public String nombreSala;
	public List<String> usuarios;
	
	public InfoSalaRespuesta(String nombreSala) {
		super();
		this.nombreSala = nombreSala;
		this.usuarios = new ArrayList<String>();
	}
}
