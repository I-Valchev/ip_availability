package main;

import java.util.Map;

public class ShutdownCommandHandler extends CommandHandler {

	public ShutdownCommandHandler(String studentName, Map<String, Student> students) {
		super(studentName, students);
	}

	@Override
	protected String perform() {
		// TODO Auto-generated method stub
		System.exit(0);
		return "ok";
	}
	
	
}
