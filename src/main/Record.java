package main;

import java.util.Date;

public class Record {
	private Date inTime, outTime;
	
	public Record(Date inTime){
		this.inTime = inTime;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
}
