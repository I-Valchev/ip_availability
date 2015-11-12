package main;

import java.util.Date;
import java.util.Map;

public class InCommandHandler extends CommandHandler {

	ClientHandler clientHandler;
	
	public InCommandHandler(String[] split, Map<String, User> students, ClientHandler clientHandler){
		super(split, students, clientHandler);
		this.executorName = split[1];
		this.clientHandler = clientHandler;
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return perform();
	}

	@Override
	protected String perform() {
		/*if(students.containsKey(executorName)){
			clientHandler = students.get(executorName);
		}else{
			clientHandler.user.name = executorName;
			students.put(executorName, clientHandler);
		}*/
		
		
		if(students.containsKey(executorName))
			clientHandler.user = students.get(executorName);
		else{
			clientHandler.user = new User(executorName);
			students.put(executorName, clientHandler.user);
		}
		login(clientHandler);
		
//		TODO how should we add a new client
		
		return "ok";
	}
	
	private void login(ClientHandler client){
		client.user.in(new Date());
	}

}
