package main;

import java.util.Map;

public class ListAvailableCommandHandler extends CommandHandler {
	
	public ListAvailableCommandHandler(String[] split, Map<String, User> students, ClientHandler clientHandler){
		super(split, students, clientHandler);
	}
	
	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		str.append("ok");
		User user;
		for(Map.Entry<String, User> iterator : students.entrySet()){
			user = iterator.getValue();
			if(user.isIn())
				str.append(":" + user.name);
		}
		
		return str.toString();
	}

}
