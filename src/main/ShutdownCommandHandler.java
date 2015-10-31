package main;

import java.util.Map;

public class ShutdownCommandHandler extends CommandHandler {

	public ShutdownCommandHandler(Map<String, Student> students) {
		super(students);
	}

	@Override
	public String perform() {
		// TODO Auto-generated method stub
		System.exit(0);
		return "ok";
	}
	
	
}
