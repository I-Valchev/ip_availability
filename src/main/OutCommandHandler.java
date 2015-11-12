package main;

import java.util.Date;
import java.util.Map;

public class OutCommandHandler extends CommandHandler {
	
	ClientHandler clientHandler;
	
	public OutCommandHandler(String[] split, Map<String, User> students, ClientHandler clientHandler){
		super(split, students, clientHandler);
		this.clientHandler = clientHandler;
	}

	@Override
	protected String perform() {
		// TODO Auto-generated method stub
		clientHandler.user.out(new Date());
		clientHandler.user = new User();
		return "ok";
	}

}
