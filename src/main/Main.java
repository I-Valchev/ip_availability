package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	static Map<String, Student> students;
	static Map<String, Class<? extends CommandHandler>> commandsList;

	public static void main(String[] args) {	
		students = new HashMap<String, Student>();
		Server s = new Server(8000);
		/*commandsList = new HashMap<String, Class<? extends CommandHandler>>();
		commandsList.put("login", InCommandHandler.class);
		commandsList.put("logout", OutCommandHandler.class);
		commandsList.put("info", InfoCommandHandler.class);
		commandsList.put("listavailable", ListAvailableCommandHandler.class);
		commandsList.put("shutdown", ShutdownCommandHandler.class);*/
		
		try {
			s.StartServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CommandHandler parse(String input){
		try{
			final String[] split = input.split(":");
			if("login".equals(split[1])){
				return new InCommandHandler(split[0], students);
			}else if("logout".equals(split[1])){
				return new OutCommandHandler(split[0], students);
			}else if("info".equals(split[1])){
				return new InfoCommandHandler(split, students);
			}else if("listavailable".equals(split[1])){
				return new ListAvailableCommandHandler(split[0], students);
			}else if("shutdown".equals(split[1])){
				return new ShutdownCommandHandler(split[0], students);
			}
		}catch (Exception e){
			throw new IllegalArgumentException("error: unknowncommand");
		}
		/*
		final String[] split = input.split(":");
		CommandHandler handler = commandsList.get(split[0]).getConstructor()
		
		return new commandsList.get(input.split(":")[0]);*/
		
		throw new IllegalArgumentException("error: unknowncommand");
	}
}
