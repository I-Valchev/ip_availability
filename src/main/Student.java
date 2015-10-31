package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student {
	public String name;
	public boolean isIn = false;

	public Record record;
	public List<Record> records;
	
	public Student(String name){
		this.name = name;
		records = new ArrayList<Record>();
	}
	
	public Student(String name, Date inTime){
		records = new ArrayList<Record>();
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
