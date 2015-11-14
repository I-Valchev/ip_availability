package main;

import java.util.Date;

public class OutCommandHandler extends CommandHandler {
	
	ClientHandler clientHandler;
	
	public OutCommandHandler(String[] split, ClientHandler clientHandler, Server server){
		super(split, clientHandler, server);
		this.clientHandler = clientHandler;
	}

	@Override
	protected String perform() {
		clientHandler.user.out(new Date());
		clientHandler.user = new User();
		return "ok";
	}

}
