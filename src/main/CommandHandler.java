package main;

public abstract class CommandHandler {

	protected String executorName, targetName;
	
	public String execute(){
		if(authenticate())
			return perform();
		return "error: not logged";
	}
	
	protected abstract String perform();
	
	public CommandHandler(String[] split, ClientHandler clientHandler){
		this.executorName = clientHandler.user.name;
		targetName = (split.length > 1) ? split[1]:null;
	}
	
	public synchronized boolean authenticate(){
		return Server.users.containsKey(executorName) && Server.users.get(executorName).isIn();
	}	
}
