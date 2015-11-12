package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static Server server;

	public static void main(String[] args) throws IOException {	
		server = new Server(9006);
		server.StartServer();
	}
}
