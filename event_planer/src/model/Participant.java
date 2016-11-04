package model;

import java.util.ArrayList;
import java.util.List;

//user that can only subscribe to events

public class Participant extends User {

	private String name;
	private Location location;
	private String phoneNumber;
	private List<Application> applications = new ArrayList<Application>();
	private boolean[] checkboxValues;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<Application> getApplications() {
		return this.applications;
	}
	
	public boolean removeApplication(Application oldApplication) {
		return applications.remove(oldApplication);
	}
	
	public boolean[] getCheckboxValues() {
		return this.checkboxValues;
	}

	public void setCheckboxValues(boolean[] checkboxValues) {
		this.checkboxValues = checkboxValues;
	}

	public Participant(String eMail, String password) {
		super(eMail, password);
		EventUserControl.getInstance().addParticipant(this);
	}
	
	/**
	 * if the participant wants to unsubscribe
	 * 
	 * @param reason -> why does the participant want to unsubscribe?
	 * 
	 * @param oldApplication ->witch application should be removed?
	 */
	public boolean signOutFromApplication(String reason, Application oldApplication){
		oldApplication.signOut(reason);
		return applications.remove(oldApplication);
		
	}
	
}