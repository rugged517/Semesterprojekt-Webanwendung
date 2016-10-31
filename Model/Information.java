package Model;

//superclass for al additional information fields

public class Information {

	private string description; //description for the input field (checkbox, date usw.)
	private boolean required = false; //is this information necessary to subscribe to the event?

	public string getDescription() {
		return this.description;
	}

	public void setDescription(string description) {
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
	public Information(string description) {
		// TODO - implement Information.Information
		throw new UnsupportedOperationException();
	}

}