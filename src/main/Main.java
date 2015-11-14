package main;

import java.io.IOException;

public class Main {
	public static Server server;

	public static void main(String[] args){
		server = new Server(8000);
		try {
			server.StartServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
