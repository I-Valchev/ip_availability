package main;

import java.util.Date;
import java.util.Map;

public class OutCommandHandler extends CommandHandler {
	
	public OutCommandHandler(String[] split, Map<String, ClientHandler> students, ClientHandler clientHandler){
		super(split, students, clientHandler);
	}

	@Override
	protected String perform() {
		// TODO Auto-generated method stub
		ClientHandler student = students.get(executorName);
		student.out(new Date());
		return "ok";
	}

}
