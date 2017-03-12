package model;

import java.sql.Timestamp;

//contains date values for additional information

public class DateField extends InformationField {

	private Timestamp minDate;
	private Timestamp maxDate;

	public Timestamp getMinDate() {
		return minDate;
	}


	public void setMinDate(Timestamp minDate) {
		this.minDate = minDate;
	}


	public Timestamp getMaxDate() {
		return maxDate;
	}


	public void setMaxDate(Timestamp maxDate) {
		this.maxDate = maxDate;
	}

	public DateField(String eventLink, String description, boolean required, String minDate, String maxDate) {
		super(eventLink, description, required);

			if(minDate.equals("")){
				minDate="1970-01-01 00:00:00.000";			
			}
			if(maxDate.equals("")){
				maxDate="1970-01-01 00:00:00.000";
			}
			this.minDate=Functions.prepareDate(minDate);
			this.maxDate=Functions.prepareDate(maxDate);


			updateType("date");
			insertSetting("minDate", minDate);
			insertSetting("maxDate", maxDate);
		
	}
	
	public DateField(int id) {
		super(id);
		
		this.minDate = Functions.prepareDate(getSettingsOption("minDate"));
		this.maxDate = Functions.prepareDate(getSettingsOption("maxDate"));
		
	}
	
}