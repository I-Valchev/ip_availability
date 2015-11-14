package CommandHandler;

import client.ClientHandler;
import main.Main;
import main.Server;

public class ShutdownCommandHandler extends CommandHandler {
	
	Server server;
	ClientHandler clientHandler;

	public ShutdownCommandHandler(String [] split, ClientHandler clientHandler, Server server) {
		super(split, clientHandler, server);
		this.server = Main.server;
		this.clientHandler = clientHandler;
	}

	@Override
	protected String perform() {
		server.parse(Server.LOGOUT_COMMAND, clientHandler).execute();
		server.stopServer();
		return "ok";
	}
	
	
}
