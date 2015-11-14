package CommandHandler;

import java.util.Map;

import client.ClientHandler;
import client.User;
import main.Server;

public class ListAvailableCommandHandler extends CommandHandler {
	
	public ListAvailableCommandHandler(String[] split, ClientHandler clientHandler, Server server){
		super(split, clientHandler, server);
	}
	
	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		str.append("ok");
		User user;
		for(Map.Entry<String, User> iterator : server.getUsers().entrySet()){
			user = iterator.getValue();
			if(user.isIn())
				str.append(":" + user.name);
		}
		
		return str.toString();
	}

}
