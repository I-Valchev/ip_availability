package main;

import java.util.Date;

public class InCommandHandler extends CommandHandler {

	ClientHandler clientHandler;

	public InCommandHandler(String[] split, ClientHandler clientHandler, Server server) {
		super(split, clientHandler, server);
		this.executorName = split[1];
		this.clientHandler = clientHandler;
	}

	@Override
	public String execute() {
		return perform();
	}

	@Override
	protected synchronized String perform() {
		
		User user;
		if(Server.users.containsKey(targetName)) //for inCommand, targetName equals executorName
			user = Server.users.get(targetName);
		else{
			user = new User(targetName);
			Server.users.put(targetName, user);
		}
		
		login(user);
		clientHandler.user = user;
		
		return "ok";
	}
	
	private synchronized void login(User user){
		if(user.isIn())
			server.removeClient(server.getClient(user));
		else
			user.in(new Date());
	}
}
