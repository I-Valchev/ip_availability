package main;

import java.util.Date;
import java.util.Map;

public class InCommandHandler extends CommandHandler {

	public InCommandHandler(String studentName, Map<String, Student> students){
		super(studentName, students);
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return perform();
	}

	@Override
	protected String perform() {
		
		Student student;
		
		if(students.containsKey(studentName)){
			student = students.get(studentName);
		}else{
			student = new Student(studentName);
			students.put(studentName, student);
		}
		
		login(student);
		
		return "ok";
	}
	
	private void login(Student student){
		student.in(new Date());
	}

}
