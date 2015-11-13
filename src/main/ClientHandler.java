package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	private final Socket socket;
	protected User user;
	
	private PrintStream out;
	private Scanner scanner;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		user = new User();
	}

	@Override
	public void run() {
		try {
			out = new PrintStream(socket.getOutputStream());

			scanner = new Scanner(socket.getInputStream());

			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				CommandHandler handler = Server.parse(line, this);
				out.println(handler.execute());
			}

			scanner.close();
			out.close();

		} catch (IOException e) {
			Server.parse(String.format(Server.LOGOUT_COMMAND), this).execute();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void stopClient() throws IOException {
		socket.close();
	}
}
