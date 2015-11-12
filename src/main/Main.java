package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	static Map<String, Student> students;
	public static Server server;

	public static void main(String[] args) throws IOException {	
		students = new HashMap<String, Student>();
		server = new Server(9003);
		
		server.StartServer();
	}
	
	public static CommandHandler parse(String input){
		try{
			final String[] split = input.split(":");
			if("login".equals(split[1])){
				return new InCommandHandler(split, students);
			}else if("logout".equals(split[1])){
				return new OutCommandHandler(split, students);
			}else if("info".equals(split[1])){
				return new InfoCommandHandler(split, students);
			}else if("listavailable".equals(split[1])){
				return new ListAvailableCommandHandler(split, students);
			}else if("shutdown".equals(split[1])){
				return new ShutdownCommandHandler(split, students, server);
			}
		}catch (Exception e){
			throw new IllegalArgumentException("error: unknowncommand");
		}
		
		throw new IllegalArgumentException("error: unknowncommand");
	}
}
