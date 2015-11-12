package main;

import java.io.IOException;

public class Main {
	public static Server server;

	public static void main(String[] args) throws IOException {
		server = new Server(8005);
		server.StartServer();
	}
}
