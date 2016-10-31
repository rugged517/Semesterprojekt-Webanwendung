package Model;

//user that can only subscribe to events

public class Participant extends User {

	private Application[] applications;
	private boolean[] checkboxValues;

	public Application[] getApplications() {
		return this.applications;
	}

	public void setApplications(Application[] applications) {
		this.applications = applications;
	}
	
	public boolean[] getCheckboxValues() {
		return this.applications;
	}

	public void setCheckboxValues(boolean[] checkboxValues) {
		this.checkboxValues = checkboxValues;
	}

}