package main;

import java.util.Map;

public class ListAvailableCommandHandler extends CommandHandler {
	
	public ListAvailableCommandHandler(String[] split, Map<String, ClientHandler> students){
		super(split, students);
	}
	
	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		str.append("ok");
		ClientHandler student;
		for(Map.Entry<String, ClientHandler> iterator : students.entrySet()){
			student = iterator.getValue();
			if(student.isIn())
				str.append(":" + student.name);
		}
		
		return str.toString();
	}

}
