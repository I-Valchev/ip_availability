package main;

import java.util.Map;

public abstract class CommandHandler {

	protected static Map<String, ClientHandler> students;
	protected String executorName, targetName;
	
	public String execute(){
		if(authenticate())
			return perform();
		return "error: not logged";
	}
	
	protected abstract String perform();
	
	public CommandHandler(String[] split, Map<String, ClientHandler> students, ClientHandler clientHandler){
		CommandHandler.students = students;
		this.executorName = clientHandler.name;
		targetName = (split.length > 1) ? split[1]:null;
	}
	
	public boolean authenticate(){
		return students.containsKey(executorName) && students.get(executorName).isIn();
	}	
}
