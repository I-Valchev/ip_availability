package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class InfoCommandHandler extends CommandHandler {

	public InfoCommandHandler(String[] split, Map<String, ClientHandler> students, ClientHandler clientHandler) {
		super(split, students, clientHandler);
	}

	@Override
	public boolean authenticate() {
		return super.authenticate() && (students.containsKey(targetName));
	}

	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		ClientHandler student = students.get(targetName);
		int loginCount = student.records.size();
		str.append("ok:" + targetName + ":" + student.isIn() + ":" + loginCount + ":");
		
		for (Record r : student.records) {
			Date inDate = r.getInTime();
			Date outDate = r.getOutTime();
			DateFormat timeFormatter = new SimpleDateFormat("yyyy-­MM-­dd'T'HH'_'mm'_'ss.SSSZ");
			str.append(timeFormatter.format(inDate));
			if (outDate != null)
				str.append(timeFormatter.format(outDate));
		}

		return str.toString();
	}

}
