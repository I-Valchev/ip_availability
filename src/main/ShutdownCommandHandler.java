package main;

public class ShutdownCommandHandler extends CommandHandler {
	
	Server server;
	ClientHandler clientHandler;

	public ShutdownCommandHandler(String [] split, ClientHandler clientHandler) {
		super(split, students, clientHandler);
		this.server = Main.server;
		this.clientHandler = clientHandler;
	}

	@Override
	protected String perform() {
		Server.parse(Server.LOGOUT_COMMAND, clientHandler).execute();
		server.stopServer();
		return "ok";
	}
	
	
}
