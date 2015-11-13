package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Server {
	protected static final String LOGOUT_COMMAND = "logout";

	private final int port;
	private ServerSocket serverSocket;
	private boolean running;
	private static List<ClientHandler> clients;
	protected static Map<String, User> users;

	public Server(int port) {
		users = new HashMap<String, User>();
		this.port = port;
		clients = Collections.synchronizedList(new LinkedList<ClientHandler>());
	}

	public void StartServer() throws IOException {
		serverSocket = createServerSocket();
		try {
			while (isRunning()) {
				final Socket socket = serverSocket.accept();
				final ClientHandler client = new ClientHandler(socket);
				clients.add(client);
				new Thread(client).start();
			}

		} catch (IOException e) {
			if (!serverSocket.isClosed())
				throw e;
		}

		stopServer();
		serverSocket.close();
	}
	
	private synchronized ServerSocket createServerSocket() throws IOException{
		if(isRunning())
			throw new IllegalStateException("Already running");
		
		setRunning(true);
		
		serverSocket = new ServerSocket(port);
		return serverSocket;
	}

	public synchronized void stopServer() {
		setRunning(false);
		try {
			for (ClientHandler client : clients) {
				client.stopClient();
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static ClientHandler getClient(User user) {
		for (ClientHandler client : clients)
			if (client.user.equals(user))
				return client;
		throw new IllegalArgumentException("No such client");
	}

	public synchronized static void removeClient(ClientHandler client) {
		try {
			client.stopClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clients.remove(client);
	}

	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}

	public static CommandHandler parse(String input, ClientHandler client) {

		final String[] split = input.split(":");
		if ("login".equals(split[0])) {
			return new InCommandHandler(split, client);
		} else if ("logout".equals(split[0])) {
			return new OutCommandHandler(split, client);
		} else if ("info".equals(split[0])) {
			return new InfoCommandHandler(split, client);
		} else if ("listavailable".equals(split[0])) {
			return new ListAvailableCommandHandler(split, client);
		} else if ("listabsent".equals(split[0])) {
			return new ListAbsentHandler(split, client);
		} else if ("shutdown".equals(split[0])) {
			return new ShutdownCommandHandler(split, client);
		}
		throw new IllegalArgumentException("error: unknown command");
	}

}
