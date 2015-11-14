package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import CommandHandler.CommandHandler;
import main.Server;

public class ClientHandler implements Runnable {
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

			out.print("въведете команда: ");
			while (scanner.hasNextLine()) {
				
				final String line = scanner.nextLine();
				CommandHandler handler = server.parse(line, this);
				out.println(handler.execute());
				out.print("въведете команда: ");
			}

			scanner.close();
			out.close();
			socket.close();

		} catch (IOException e) {
			server.parse(String.format(Server.LOGOUT_COMMAND), this).execute();
			try {
				stopClient();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void stopClient() throws IOException {
		socket.close();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
