package work_01_refactor;

import java.util.Date;
import java.util.Map;

public class OutCommandHandler extends CommandHandler {

	private String studentName;
	private Map<String, Student> students;
	
	public OutCommandHandler(String studentName, Map<String, Student> students){
		super(students);
		this.students = students;
		this.studentName = studentName;
	}

	@Override
	public String perform() {
		// TODO Auto-generated method stub

		Student student = students.get(studentName);
		student.out(new Date());
		
		return "ok";
	}

}
