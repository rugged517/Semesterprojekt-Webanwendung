package model;

import java.sql.Date;

//contains all additional information from one participant for one application

public class PersonalInformation {

	private int[] selectValueIds; //IDs of the selected values in dropdown menus
	private String[] textValues;
	private int[] checkboxValueIds; //IDs of the checked checkboxes
	private Date[] dateValues;
	
	public int[] getSelectValueIds() {
		return selectValueIds;
	}
	public void setSelectValueIds(int[] selectValueIds) {
		this.selectValueIds = selectValueIds;
	}
	public String[] getTextValues() {
		return textValues;
	}
	public void setTextValues(String[] textValues) {
		this.textValues = textValues;
	}
	public int[] getCheckboxValueIds() {
		return checkboxValueIds;
	}
	public void setCheckboxValueIds(int[] checkboxValueIds) {
		this.checkboxValueIds = checkboxValueIds;
	}
	public Date[] getDateValues() {
		return dateValues;
	}
	public void setDateValues(Date[] dateValues) {
		this.dateValues = dateValues;
	}

}