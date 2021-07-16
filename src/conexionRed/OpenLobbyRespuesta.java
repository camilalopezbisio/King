package conexionRed;


import java.util.List;

import kingdomino.SalaDeJuego;

public class OpenLobbyRespuesta {
	public List<SalaDeJuego>listaSalaClientes;

	public OpenLobbyRespuesta(List<SalaDeJuego> listaSalaClientes) {
		this.listaSalaClientes = listaSalaClientes;
	}
	
}
