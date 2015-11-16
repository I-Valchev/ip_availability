package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import CommandHandler.CommandHandler;
import main.Server;

public class ClientHandler implements Runnable {
	private static final String INPUT_MESSAGE = "въведете команда: ";
	private final Socket socket;
	private User user;
	private final Server server;

	private PrintStream out;
	private Scanner scanner;

	public ClientHandler(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		setUser(new User());
	}

	@Override
	public void run() {

		try {
			out = new PrintStream(socket.getOutputStream());
			scanner = new Scanner(socket.getInputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		CommandHandler handler = null;
		
		while (out.printf(INPUT_MESSAGE) != null && scanner.hasNextLine()) {
			
			final String line = scanner.nextLine();
			
			try {
				handler = server.parse(line, this);
			} catch (IllegalArgumentException | NullPointerException e) {
				out.println(e.getLocalizedMessage());
				continue;
			} catch (Exception e) {
				server.parse(Server.LOGOUT_COMMAND, this).execute();
				stopClient();
				e.printStackTrace();
				break;
			}

			out.println(handler.execute());
		}

		out.close();
		scanner.close();
		stopClient();
	}

	public void stopClient(){
		out.close();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
