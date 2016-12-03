package model;

import java.util.ArrayList;
import java.util.List;

import data.DBConnect;

//user that can only subscribe to events

public class Participant extends User {

	protected String name;
	protected Location location;
	protected int phoneNumber;
	protected List<Application> applications = new ArrayList<Application>();
	protected boolean[] checkboxValues;

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

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
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
		
		//check in DB if table participants contains eMail
		DBConnect dbConnect =DBConnect.getInstance();
		exists =dbConnect.checkForParticipant(eMail);	
		
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