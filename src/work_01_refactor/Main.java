package work_01_refactor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	static Map<String, Student> students;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		initialization
		System.out.println("hello");
		students = new HashMap<String, Student>();
		Scanner in = new Scanner(System.in);
		String input;
		
//		logic/looping
		while(true){
			input = in.nextLine();
			
			CommandHandler handler = parse(input);
			System.out.println(handler.perform());
		}
	}
	
	
	public static CommandHandler parse(String input){
		final String[] split = input.split(":");
		
		if("login".equals(split[1])){
			return new InCommandHandler(split[0], students);
		}else if("logout".equals(split[1])){
			return new OutCommandHandler(split[0], students);
		}else if("info".equals(split[1])){
			return new InfoCommandHandler(split, students);
		}else if("listavailable".equals(split[1])){
			return new ListAvailableCommandHandler(students);
		}else if("shutdown".equals(split[1])){
			return new ShutdownCommandHandler(students);
		}
		throw new IllegalArgumentException("error: unknowncommand");
	}

}
