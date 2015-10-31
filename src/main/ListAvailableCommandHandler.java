package main;

import java.util.Map;

public class ListAvailableCommandHandler extends CommandHandler {
	private Map<String, Student> students;
	
	public ListAvailableCommandHandler(String studentName, Map<String, Student> students){
		super(studentName, students);
	}
	
	@Override
	protected String perform() {
		Student student;
		StringBuilder str = new StringBuilder();
		str.append("ok");
		
		for(Map.Entry<String, Student> iterator : students.entrySet()){
			student = iterator.getValue();
			if(student.isIn)
				str.append(":" + student.name);
		}
		
		return str.toString();
	}

}
