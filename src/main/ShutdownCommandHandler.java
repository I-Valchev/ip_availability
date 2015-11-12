package main;

import java.util.Map;

public class ShutdownCommandHandler extends CommandHandler {
	
	Server server;

	public ShutdownCommandHandler(String [] split, Map<String, Student> students, Server server) {
		super(split, students);
		this.server = server;
	}

	@Override
	protected String perform() {
		server.stopServer();
		return "ok";
	}
	
	
}
