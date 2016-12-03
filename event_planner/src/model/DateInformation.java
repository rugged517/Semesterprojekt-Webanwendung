package model;

import java.util.Date;

//contains date values for additional information

public class DateInformation extends Information {

	private Date minDate;
	private Date maxDate;

	public Date getMinDate() {
		return this.minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	
	public Date getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public DateInformation(String description) {
		super(description);
	}
	
}