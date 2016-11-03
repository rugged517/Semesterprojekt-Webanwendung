package model;

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
		return this.checkboxValues;
	}

	public void setCheckboxValues(boolean[] checkboxValues) {
		this.checkboxValues = checkboxValues;
	}

	public Participant(String eMail, String password) {
		super(eMail, password);
	}
	
	/**
	 * if the participant wants to unsubscribe
	 * 
	 * @param reason -> why does the participant want to unsubscribe?
	 * 
	 * @param oldApplication ->witch application should be removed?
	 */
	public void signOutFromApplication(String reason, Application oldApplication){
		oldApplication.signOut(reason);
		applications=Functions.removeApplication(oldApplication, applications);
		
	}
	
}