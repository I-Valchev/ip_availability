package main;

import java.util.Map;

public abstract class CommandHandler {

	protected static Map<String, Student> students;
	protected String executorName, targetName;
	
	public String execute(){
		if(authenticate())
			return perform();
		return "error: not logged";
	}
	
	protected abstract String perform();
	
	public CommandHandler(String[] split, Map<String, Student> students){
		CommandHandler.students = students;
		this.executorName = split[0];
		targetName = (split.length > 2) ? split[2]:null;
	}
	
	public boolean authenticate(){
		return students.containsKey(executorName) && students.get(executorName).isIn();
	}	
}
