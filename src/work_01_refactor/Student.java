package work_01_refactor;

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
		if(!isIn){
			records.add(new Record(inTime));
			isIn = true;
		}else{
			System.out.println("Watch out! Student already in!");
			return;
		}
		
	}
	
	public void out(Date outTime){
		if(isIn){
			Record lastRecord = records.get(records.size()-1);
			lastRecord.setOutTime(outTime);
			records.set(records.size()-1, lastRecord);
			isIn = false;
		}else{
			System.out.println("No such student in!");
		}
	}
}
