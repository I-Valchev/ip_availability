package main;

import java.util.Date;
import java.util.Map;

public class OutCommandHandler extends CommandHandler {
	
	public OutCommandHandler(String studentName, Map<String, Student> students){
		super(studentName, students);
	}

	@Override
	protected String perform() {
		// TODO Auto-generated method stub
		Student student = students.get(studentName);
		student.out(new Date());
		return "ok";
	}

}
