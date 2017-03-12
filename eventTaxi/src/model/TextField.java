package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//contains settings for textfields

public class TextField extends InformationField {

	private int min;
	private int max;
	private boolean isTextarea;

	public int getMin() {
		return this.min;
	}

	public void setMin(int minLength) {
		this.min = minLength;
	}

	public int getMax() {
		return this.max;
	}

	public void setMax(int maxLength) {
		this.max = maxLength;
	}

	public boolean isTextarea() {
		return this.isTextarea;
	}

	public void SetIsTextarea(boolean isTextarea) {
		this.isTextarea = isTextarea;
	}

	public TextField(String eventLink, String description, boolean required, boolean isTextarea, int min, int max) {
		super(eventLink, description, required);

		this.min = min;
		this.max = max;
		this.isTextarea = isTextarea;

		updateType("text");
		insertSetting("min", min);
		insertSetting("max", max);
		insertSetting("textarea", isTextarea);
	}

	public TextField(int id) {
		super(id);
		
		this.min = Integer.parseInt(getSettingsOption("min"));
		this.max = Integer.parseInt(getSettingsOption("max"));
		this.isTextarea = getSettingsOption("textarea").equals("true");
		
		
	}

}