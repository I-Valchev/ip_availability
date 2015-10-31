package main;

import java.util.Map;

public class InfoCommandHandler extends CommandHandler {

	private String studentName;
	private Map<String, Student> students;
	
	public InfoCommandHandler(String[] names, Map<String, Student> students){
		super(students);
		this.students = students;
		this.studentName = names[2]; //the 2nd element is the name of the student
	}
	
	
			/*for(Record r : student.records){
				
				Date inDate = r.getInTime();
				Date outDate = r.getOutTime();
				DateFormat timeSpentFormatter = new SimpleDateFormat("mm:ss");
				DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
				
				if(outDate == null){
					Date timeSpent = new Date(new Date().getTime() - inDate.getTime());
					System.out.println(studentName + " is in since " + timeFormatter.format(inDate) + ". Time spent since " + timeSpentFormatter.format(timeSpent));
				}else{
					Date timeSpent = new Date(outDate.getTime() - inDate.getTime());
					System.out.println(studentName + " was in from " + timeFormatter.format(inDate) 
						+ " to " + timeFormatter.format(outDate) + ". Time spent in " + timeSpentFormatter.format(timeSpent));
				}
				
				
			}*/	

	@Override
	public String perform() {
		Student student = students.get(studentName);
		int loginCount = student.records.size();	
		
		return ("ok:" + studentName + ":" + student.isIn + ":" + loginCount);
	}
	
}
