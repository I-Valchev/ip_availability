package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class InfoCommandHandler extends CommandHandler {

	public InfoCommandHandler(String[] split, Map<String, Student> students) {
		super(split, students);
	}

	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		if (students.containsKey(targetName)) {
			Student student = students.get(targetName);
			int loginCount = student.records.size();
			str.append("ok:" + targetName + ":" + student.isIn()+":" + loginCount + ":");
			for (Record r : student.records) {
				Date inDate = r.getInTime();
				Date outDate = r.getOutTime();
				DateFormat timeFormatter = new SimpleDateFormat("yyyy-­MM-­dd'T'HH'_'mm'_'ss.SSSZ");
				str.append(timeFormatter.format(inDate));
				if (outDate != null)
					str.append(timeFormatter.format(outDate));
			}
		}else{
			str.append("error:notlogged");
		}
		
		return str.toString();

	}

}
