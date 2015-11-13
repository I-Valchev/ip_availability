package main;

import java.util.Date;
import java.util.Map;

public class InCommandHandler extends CommandHandler {

	ClientHandler clientHandler;

	public InCommandHandler(String[] split, Map<String, User> students, ClientHandler clientHandler) {
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
	protected synchronized String perform() {
		
		User user;
		if(students.containsKey(targetName))
			user = students.get(targetName);
		else{
			user = new User(targetName);
			students.put(targetName, user);
		}
		
		login(user);
		clientHandler.user = user;
		
		return "ok";
	}
	
	private synchronized void login(User user){
		if(user.isIn())
			Server.removeClient(Server.getClient(user));
		else
			user.in(new Date());
	}
}
