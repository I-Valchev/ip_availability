package main;

import java.io.IOException;

public class Main {
	public static Server server;

	public static void main(String[] args) throws IOException {	
		server = new Server(9010);
		server.StartServer();
	}
}
