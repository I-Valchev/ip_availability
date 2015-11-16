package CommandHandler;

import client.ClientHandler;
import main.Server;

public abstract class CommandHandler {
	
	protected static final String OK_COMMAND = "ok";
	protected String executorName, targetName;
	protected Server server;
	
	public String execute(){
		if(authenticate())
			return perform();
		return "error: not logged";
	}
	
	protected abstract String perform();
	
	public CommandHandler(String[] split, ClientHandler clientHandler, Server server){
		this.executorName = clientHandler.getUser().name;
		this.server = server;
		targetName = (split.length > 1) ? split[1]:null;
	}
	
	public synchronized boolean authenticate(){
		return server.getUsers().containsKey(executorName) && server.getUsers().get(executorName).isIn();
	}	
}
