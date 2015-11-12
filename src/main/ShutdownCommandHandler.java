package main;

import java.util.Map;

public class ShutdownCommandHandler extends CommandHandler {
	
	Server server;

	public ShutdownCommandHandler(String [] split) {
		super(split, students);
		this.server = Main.server;
	}

	@Override
	protected String perform() {
		server.stopServer();
		return "ok";
	}
	
	
}
