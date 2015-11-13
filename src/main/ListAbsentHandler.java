package main;

import java.util.Map;

public class ListAbsentHandler extends CommandHandler {

	public ListAbsentHandler(String[] split, ClientHandler clientHandler) {
		super(split, clientHandler);
	}

	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		str.append("ok");
		User user;
		for(Map.Entry<String, User> iterator : Server.users.entrySet()){
			user = iterator.getValue();
			if(!user.isIn())
				str.append(":" + user.name);
		}
		
		return str.toString();
	}

}
