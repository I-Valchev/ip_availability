package main;

import java.util.Map;

public class ShutdownCommandHandler extends CommandHandler {

	public ShutdownCommandHandler(String [] split, Map<String, Student> students) {
		super(split, students);
	}

	@Override
	protected String perform() {
		// TODO Auto-generated method stub
//		server.stop();
		System.exit(0);
		return "ok";
	}
	
	
}
