package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoCommandHandler extends CommandHandler {

	public InfoCommandHandler(String[] split, ClientHandler clientHandler, Server server) {
		super(split, clientHandler, server);
	}

	@Override
	public boolean authenticate() {
		return super.authenticate() && (Server.users.containsKey(targetName));
	}

	@Override
	protected String perform() {
		StringBuilder str = new StringBuilder();
		User user = Server.users.get(targetName);
		int loginCount = user.records.size();
		str.append("ok:" + targetName + ":" + user.isIn() + ":" + loginCount);
		
		for (Record r : user.records) {
			Date inDate = r.getInTime();
			Date outDate = r.getOutTime();
			DateFormat timeFormatter = new SimpleDateFormat("yyyy-­MM-­dd'T'HH'_'mm'_'ss.SSSZ");
			str.append(":"+timeFormatter.format(inDate));
			if (outDate != null)
				str.append(":" + timeFormatter.format(outDate));
		}

		return str.toString();
	}

}
