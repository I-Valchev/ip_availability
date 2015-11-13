package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	public String name = null;
	private boolean isIn = false;

	public List<Record> records;
	
	public User(){
		records = new ArrayList<Record>();
	}
	
	public User(String name){
		this();
		this.name = name;
	}
	
	public User(String name, Date inTime){
		this(name);
		this.in(inTime);
		isIn = true;
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