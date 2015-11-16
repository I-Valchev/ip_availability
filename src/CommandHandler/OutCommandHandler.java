package CommandHandler;

import java.util.Date;

import client.ClientHandler;
import client.User;
import main.Server;

public class OutCommandHandler extends CommandHandler {
	
	ClientHandler clientHandler;
	
	public OutCommandHandler(String[] split, ClientHandler clientHandler, Server server){
		super(split, clientHandler, server);
		this.clientHandler = clientHandler;
	}

	@Override
	protected String perform() {
		clientHandler.getUser().out(new Date());
		clientHandler.setUser(new User());
		return OK_COMMAND;
	}

}
