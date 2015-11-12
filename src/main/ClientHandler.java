package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	private final Socket socket;
	protected User user;

	private static final String LOGOUT_COMMAND = "logout";

	public ClientHandler(Socket socket) {
		this.socket = socket;
		user = new User();
	}

	@Override
	public void run() {
		try {
			PrintStream out = new PrintStream(socket.getOutputStream());

			final Scanner scanner = new Scanner(socket.getInputStream());

			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				CommandHandler handler = Server.parse(line, this);
				out.println(handler.execute());
			}

			scanner.close();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Server.parse(String.format(LOGOUT_COMMAND), this).execute();
		}
	}

	public void stopClient() throws IOException {
		socket.close();
	}
}
