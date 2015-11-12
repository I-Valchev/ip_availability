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
	private final int port;

	private boolean running;
	private final List<ClientHandler> clients;
	static Map<String, ClientHandler> students;
	
	public Server(int port){
		students = new HashMap<String, ClientHandler>();
		this.port = port;
		clients = Collections.synchronizedList(new LinkedList<ClientHandler>());
	}
	
	public void StartServer() throws IOException{
		final ServerSocket serverSocket = new ServerSocket(port);
		setRunning(true);
		
		while(isRunning()){
			
			final Socket socket = serverSocket.accept();
			final ClientHandler client = new ClientHandler(socket);
			clients.add(client);
			new Thread(client).start();
		}
		
		serverSocket.close();
	}	
	
	public synchronized void stopServer(){
		setRunning(false);
		try {
			for(ClientHandler client : clients){
				client.stopClient();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	public void OnClientStopped(ClientHandler client){
		clients.remove(client);
	}
	
	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}
	
	public static CommandHandler parse(String input, ClientHandler client){
		
		final String[] split = input.split(":");
		if("login".equals(split[0])){
			return new InCommandHandler(split, students, client);
		}if("logout".equals(split[0])){
			return new OutCommandHandler(split, students, client);
		}else if("info".equals(split[0])){
			return new InfoCommandHandler(split, students, client);
		}else if("listavailable".equals(split[0])){
			return new ListAvailableCommandHandler(split, students, client);
		}else if("shutdown".equals(split[0])){
			return new ShutdownCommandHandler(split, client);
		}
		
		throw new IllegalArgumentException("error: unknown command");
	}
	
}