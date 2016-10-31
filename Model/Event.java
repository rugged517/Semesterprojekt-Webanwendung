package Model;

import java.util.Date;

//contains informations about an event and an array of applications

public class Event {

	private string title;
	private string link;
	private string text;
	private string eMail;
	private Location location;
	private Date date;
	private Date deadline;
	private int minParticipants = 0;
	private int maxParticipants = 0;
	private AdditionalInformation additionalInformation;
	private Application[] applications;

	public string getTitle() {
		return this.title;
	}

	public void setTitle(string title) {
		this.title = title;
	}

	public string getLink() {
		return this.link;
	}

	public string getText() {
		return this.text;
	}

	public void setText(string text) {
		this.text = text;
	}

	public string getEMail() {
		return this.eMail;
	}

	public void setEMail(string eMail) {
		this.eMail = eMail;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMinParticipants() {
		return this.minParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	public int getMaxParticipants() {
		return this.maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public AdditionalInformation getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Application[] getApplications() {
		return this.applications;
	}

	public void setApplications(Application[] applications) {
		this.applications = applications;
	}

	
	//returns all registered participants
	public Participant[] getListOfParticipants() {
		// TODO - implement Event.getListOfParticipants
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reason ->why cancel the event?
	 * @param message ->message to the subscribed particpants
	 */
	public void cancelEvent(string reason, string message) {
		// TODO - implement Event.cancelEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * needs to generate an unique link
	 * @param title
	 * @param location
	 * @param date
	 */
	public Event(string title, Location location, datetime date) {
		// TODO - implement Event.Event
		throw new UnsupportedOperationException();
	}

}