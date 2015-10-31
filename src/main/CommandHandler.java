package main;

import java.util.Map;

public abstract class CommandHandler {

	protected Map<String, Student> students;
	protected String studentName;
	
	public String execute(){
		authenticate();
		return perform();
	}
	
	protected abstract String perform();
	
	public CommandHandler(String studentName, Map<String, Student> students){
		this.students = students;
		this.studentName = studentName;
	}
	
	public boolean authenticate(){
		return this.students.containsKey(studentName);
	}
	
}
