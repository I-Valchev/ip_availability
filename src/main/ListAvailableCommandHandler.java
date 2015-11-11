package main;

import java.util.Map;

public class ListAvailableCommandHandler extends CommandHandler {
	
	public ListAvailableCommandHandler(String[] split, Map<String, Student> students){
		super(split, students);
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
