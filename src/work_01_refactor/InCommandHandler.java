package work_01_refactor;

import java.util.Date;
import java.util.Map;

public class InCommandHandler extends CommandHandler {
	private String studentName;
	private Map<String, Student> students;
	
	public InCommandHandler(String studentName, Map<String, Student> students){
		super(students);
		this.students = students;
		this.studentName = studentName;
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return perform();
	}

	@Override
	public String perform() {
		if(students.containsKey(studentName)){
			Student oldStudent = students.get(studentName);
			oldStudent.in(new Date());
		}else{
			Student newStudent = new Student(studentName);
			newStudent.in(new Date());
			students.put(studentName, newStudent);
		}
		return "ok";
	}

}
