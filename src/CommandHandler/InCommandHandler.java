package CommandHandler;

import java.util.Date;

import client.ClientHandler;
import client.User;
import main.Server;

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
		if(server.getUsers().containsKey(targetName)) //for inCommand, targetName equals executorName
			user = server.getUsers().get(targetName);
		else{
			user = new User(targetName);
			server.getUsers().put(targetName, user);
		}
		
		login(user);
		clientHandler.setUser(user);
		
		return "ok";
	}
	
	private synchronized void login(User user){
		if(user.isIn())
			server.removeClient(server.getClient(user));
		else
			user.in(new Date());
	}
}
