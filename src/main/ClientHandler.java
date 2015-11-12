package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.sun.net.ssl.internal.www.protocol.https.Handler;

public class ClientHandler implements Runnable{
	private final Socket socket;
	public String name = null;
	private boolean isIn = false;
	public List<Record> records;
	private static final String LOGOUT_COMMAND = "%s:logout";

	public ClientHandler(Socket socket) {
		this.socket = socket;
		records = new ArrayList<Record>();
	}
	
	@Override
	public void run(){
		try {
			PrintStream out = new PrintStream(socket.getOutputStream());
		
			final Scanner scanner = new Scanner(socket.getInputStream());
		
			while(scanner.hasNextLine()){
				final String line = scanner.nextLine();
				new Main();
				CommandHandler handler = Server.parse(line, this);
				out.println(handler.execute());
			}	
			scanner.close();
			out.close();
			
		}catch (IOException e) {
				e.printStackTrace();
		}finally{
			if(socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			Server.parse(String.format(LOGOUT_COMMAND, name), this).execute();
		}
	}
	
	public void stopClient() throws IOException{
		socket.close();
	}
	
	public void in(Date inTime){
		records.add(new Record(inTime));
		isIn = true;
	}
	
	public void out(Date outTime){
		Record lastRecord = records.get(records.size()-1);
		lastRecord.setOutTime(outTime);
		records.set(records.size()-1, lastRecord);
		isIn = false;
	}
	
	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}
}
