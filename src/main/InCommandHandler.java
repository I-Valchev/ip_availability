package main;

import java.util.Date;
import java.util.Map;

public class InCommandHandler extends CommandHandler {

	public InCommandHandler(String[] split, Map<String, Student> students){
		super(split, students);
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return perform();
	}

	@Override
	protected String perform() {
		
		Student student;
		
		if(students.containsKey(executorName)){
			student = students.get(executorName);
		}else{
			student = new Student(executorName);
			students.put(executorName, student);
		}
		
		login(student);
		
		return "ok";
	}
	
	private void login(Student student){
		student.in(new Date());
	}

}
