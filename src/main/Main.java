package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	static Map<String, Student> students;

	public static void main(String[] args) {

//		initialization
		/*students = new HashMap<String, Student>();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String input;
		
//		logic/looping
		while(true){
			input = in.nextLine();
			
			CommandHandler handler = parse(input);
			System.out.println(handler.execute());
		}*/
		
		Server s = new Server(8000);
		try {
			s.StartServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
