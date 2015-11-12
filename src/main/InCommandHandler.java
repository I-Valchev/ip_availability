package main;

import java.util.Date;
import java.util.Map;

public class InCommandHandler extends CommandHandler {

	ClientHandler clientHandler;
	
	public InCommandHandler(String[] split, Map<String, ClientHandler> students, ClientHandler clientHandler){
		super(split, students);
		this.clientHandler = clientHandler;
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return perform();
	}

	@Override
	protected String perform() {
			
		if(students.containsKey(executorName)){
			clientHandler = students.get(executorName);
		}else{
			clientHandler.name = executorName;
			students.put(executorName, clientHandler);
		}
		
		login(clientHandler);
		
//		TODO how should we add a new client
		
		return "ok";
	}
	
	private void login(ClientHandler student){
		student.in(new Date());
	}

}
