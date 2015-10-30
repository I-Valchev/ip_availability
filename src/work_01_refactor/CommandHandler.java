package work_01_refactor;

import java.util.Map;

public abstract class CommandHandler {

	private String[] input;
	Map<String, Student> students;
	
	/*public String execute() {
		// TODO Auto-generated method stub
	}*/
	
	public String execute(){
		authenticate();
		perform();
		return "ok";
	}
	
	public abstract String perform();
	
	public CommandHandler(Map<String, Student> students){
		this.students = students;
	}
	
	public boolean authenticate(){
		return this.students.containsKey(input[0]);
	}
	
}
