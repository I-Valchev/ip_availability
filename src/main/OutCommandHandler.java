package main;

import java.util.Date;
import java.util.Map;

public class OutCommandHandler extends CommandHandler {
	
	public OutCommandHandler(String[] split, Map<String, Student> students){
		super(split, students);
	}

	@Override
	protected String perform() {
		// TODO Auto-generated method stub
		Student student = students.get(executorName);
		student.out(new Date());
		return "ok";
	}

}
