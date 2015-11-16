package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import CommandHandler.CommandHandler;
import CommandHandler.InCommandHandler;
import CommandHandler.InfoCommandHandler;
import CommandHandler.ListAbsentCommandHandler;
import CommandHandler.ListAvailableCommandHandler;
import CommandHandler.OutCommandHandler;
import CommandHandler.ShutdownCommandHandler;
import client.ClientHandler;
import client.User;

public class Server {
	
	private static final String ERROR_UNKNOWN_COMMAND = "error: unknown command";
	private static final String NO_SUCH_CLIENT = "No such client";
	private static final String LOGIN_COMMAND = "login", INFO_COMMAND = "info", LISTAVAILABLE_COMMAND = "listavailable",
			LISTABSENT_COMMAND = "listabsent", SHUTDOWN_COMMAND = "shutdown";
	public static final String LOGOUT_COMMAND = "logout";

	private final int port;
	private ServerSocket serverSocket;
	private boolean running;
	private List<ClientHandler> clients;
	private Map<String, Class<? extends CommandHandler>> commandsList;
	private Map<String, User> users;

	public Server(int port) {
		setUsers(new HashMap<String, User>());
		this.port = port;
		setClients(Collections.synchronizedList(new LinkedList<ClientHandler>()));

		commandsList = new HashMap<String, Class<? extends CommandHandler>>();
		commandsList.put(LOGIN_COMMAND, InCommandHandler.class);
		commandsList.put(LOGOUT_COMMAND, OutCommandHandler.class);
		commandsList.put(INFO_COMMAND, InfoCommandHandler.class);
		commandsList.put(LISTAVAILABLE_COMMAND, ListAvailableCommandHandler.class);
		commandsList.put(LISTABSENT_COMMAND, ListAbsentCommandHandler.class);
		commandsList.put(SHUTDOWN_COMMAND, ShutdownCommandHandler.class);
	}

	public void StartServer() throws IOException {
		serverSocket = createServerSocket();
		try {
			while (isRunning()) {
				final Socket socket = serverSocket.accept();
				final ClientHandler client = new ClientHandler(socket, this);
				getClients().add(client);
				new Thread(client).start();
			}

		} catch (IOException e) {
			if (!serverSocket.isClosed())
				throw e;
		}

		stopServer();
	}

	private synchronized ServerSocket createServerSocket() throws IOException {
		if (isRunning())
			throw new IllegalStateException("Already running");

		setRunning(true);

		serverSocket = new ServerSocket(port);
		return serverSocket;
	}

	public synchronized void stopServer() {
		setRunning(false);
		try {
			for (ClientHandler client : getClients()) {
				client.stopClient();
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized ClientHandler getClient(User user) {
		for (ClientHandler client : getClients())
			if (client.getUser().equals(user))
				return client;
		throw new IllegalArgumentException(NO_SUCH_CLIENT);
	}

	public synchronized void removeClient(ClientHandler client) {
		client.stopClient();
		getClients().remove(client);
	}

	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}

	public CommandHandler parse(String input, ClientHandler client) throws IllegalArgumentException {
		final String[] split = input.split(":");

		try {
			return (CommandHandler) commandsList.get(split[0])
					.getConstructor(String[].class, ClientHandler.class, Server.class).newInstance(split, client, this);
		} catch (Exception e) {
			throw new IllegalArgumentException(ERROR_UNKNOWN_COMMAND);
		}

	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public List<ClientHandler> getClients() {
		return clients;
	}

	public void setClients(List<ClientHandler> clients) {
		this.clients = clients;
	}

}
