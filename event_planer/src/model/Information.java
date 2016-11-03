package model;

//superclass for all additional information fields

public class Information {

	private String description; //description for the input field (checkbox, date usw.)
	private boolean required = false; //is this information necessary to subscribe to the event?

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRequired() {
		return this.required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * 
	 * @param description -> Which information should the user enter?
	 */
	public Information(String description) {
		this.description=description;
	}

}