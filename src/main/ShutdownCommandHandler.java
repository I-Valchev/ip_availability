package main;

public class ShutdownCommandHandler extends CommandHandler {
	
	Server server;

	public ShutdownCommandHandler(String [] split, ClientHandler clientHandler) {
		super(split, students, clientHandler);
		this.server = Main.server;
	}

	@Override
	protected String perform() {
		server.stopServer();
		return "ok";
	}
	
	
}
