package main;

import java.util.Map;

public class InfoCommandHandler extends CommandHandler {
	
	public InfoCommandHandler(String[] split, Map<String, Student> students){
		super(split, students);
	}

	@Override
	protected String perform() {		
		Student student = students.get(targetName);
		int loginCount = student.records.size();	
		return ("ok:" + targetName + ":" + student.isIn() + ":" + loginCount);
	}
	
}
