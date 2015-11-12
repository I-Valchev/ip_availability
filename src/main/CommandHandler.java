package main;

import java.util.Map;

public abstract class CommandHandler {

	protected static Map<String, User> students;
	protected String executorName, targetName;
	
	public String execute(){
		if(authenticate())
			return perform();
		return "error: not logged";
	}
	
	protected abstract String perform();
	
	public CommandHandler(String[] split, Map<String, User> students, ClientHandler clientHandler){
		CommandHandler.students = students;
		this.executorName = clientHandler.user.name;
		targetName = (split.length > 1) ? split[1]:null;
	}
	
	public boolean authenticate(){
		return students.containsKey(executorName) && students.get(executorName).isIn();
	}	
}
