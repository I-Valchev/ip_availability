package main;

import java.util.Map;

public class InfoCommandHandler extends CommandHandler {
	
	public InfoCommandHandler(String[] names, Map<String, Student> students){
		super(names[2], students);
	}

	@Override
	protected String perform() {		
		Student student = students.get(studentName);
		int loginCount = student.records.size();	
		return ("ok:" + studentName + ":" + student.isIn() + ":" + loginCount);
	}
	
}
