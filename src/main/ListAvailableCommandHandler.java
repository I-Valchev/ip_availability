package main;

import java.util.Map;

public class ListAvailableCommandHandler extends CommandHandler {
	
	public ListAvailableCommandHandler(String studentName, Map<String, Student> students){
		super(studentName, students);
	}
	
	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		str.append("ok");
		Student student;
		for(Map.Entry<String, Student> iterator : students.entrySet()){
			student = iterator.getValue();
			if(student.isIn())
				str.append(":" + student.name);
		}
		
		return str.toString();
	}

}
