package work_01_refactor;

import java.util.Map;

public class ListAvailableCommandHandler extends CommandHandler {
	private Map<String, Student> students;
	
	public ListAvailableCommandHandler(Map<String, Student> students){
		super(students);
		this.students = students;
	}
	
	@Override
	public String perform() {
		Student student;
		StringBuilder str = new StringBuilder();
		str.append("ok");
		
		for(Map.Entry<String, Student> iterator : students.entrySet()){
			student = iterator.getValue();
			if(student.isIn)
				str.append(":" + student.name);
		}
		
		return str.toString();
	}

}
