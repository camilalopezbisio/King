package conexionRed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import InterfazGrafica.GenericResponse;
import InterfazGrafica.Lobby;
import kingdomino.SalaDeJuego;

///esto es parte del usuario
public class ServerThread implements Runnable {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader userIn;
	private BufferedReader serverIn;
	private Lobby lobby;
	private String name;
	private HashMap<String, SalaDeJuego> salas;

	public ServerThread(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
		salas = new HashMap<String, SalaDeJuego>();
	}

	@Override
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			userIn = new BufferedReader(new InputStreamReader(System.in));
			GenericResponse mensajeRecibido;
			PrintStream ps = new PrintStream(socket.getOutputStream(), true);
			Gson gson = new Gson();
			while (!socket.isClosed()) {
				if (serverIn.ready()) {
					String input = serverIn.readLine();
					if (input != null) {

						mensajeRecibido = gson.fromJson(input, GenericResponse.class);
						switch (mensajeRecibido.nombreComando) {
						case "OPENLOBBY":

							OpenLobbyRespuesta respuestaOpenLobby = gson.fromJson(mensajeRecibido.gson,
									OpenLobbyRespuesta.class);

							lobby = new Lobby(respuestaOpenLobby.listaSalaClientes, name, socket);
							lobby.setVisible(true);

							break;

						case "OPENSALA":
							InfoSalaRespuesta respuestaSala = gson.fromJson(mensajeRecibido.gson,
									InfoSalaRespuesta.class);

//							salas.put(respuestaSala.nombreSala, new SalaDeJuego(name, respuestaSala.nombreSala, socket));
							salas.put(respuestaSala.nombreSala, new SalaDeJuego(respuestaSala.nombreSala, socket));
//							salas.get(respuestaSala.nombreSala).setVisible(true);
//							salas.get(respuestaSala.nombreSala).addLista(respuestaSala.usuarios);
							;

							break;

						case "ACTUALIZARSALA":
							InfoSalaRespuesta respuestaSalaActualizacion = gson.fromJson(mensajeRecibido.gson,
									InfoSalaRespuesta.class);
//							salas.get(respuestaSalaActualizacion.nombreSala)
//									.addLista(respuestaSalaActualizacion.usuarios);

							break;

						case "UPDATELOBBY":
							UpdateSalaRespuesta updateSalas = gson.fromJson(mensajeRecibido.gson,
									UpdateSalaRespuesta.class);

							this.lobby.actualizar(updateSalas.lista, socket);

							break;

						case "UPDATESALA":
							Mensaje mensaje = gson.fromJson(mensajeRecibido.gson, Mensaje.class);

//							salas.get(mensaje.getDestino()).actualizar(mensaje.getMensaje());

							break;

						default:
							System.out.println(mensajeRecibido.nombreComando);
							break;
						}
					}
				}
				if (userIn.ready()) {
					out.println(name + " > " + userIn.readLine());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
